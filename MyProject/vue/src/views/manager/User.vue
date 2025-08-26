<template>
  <div>
    <div style="width: 100%;height:1000px;padding:40px 40px; background-color: white;border-radius: 4px">
      <!--按钮 和 搜索框样式-->
      <div style="margin-bottom: 40px;font-size: 18px;font-weight: 530;">用户管理</div>
      <div style="margin-bottom: 20px; display: flex">
        <div style="flex: 10;text-align: left">
          <el-input placeholder="请输入查询内容" size="small"  v-model="input" style="width: 20%; margin-right: 20px"><i slot="suffix" class="el-input__icon el-icon-search"></i></el-input>
        </div>
        <div style="flex:2;text-align: right">
          <el-button type="success" size="small" style="border-radius: 1px;width: 100px;text-align: center" @click="add" v-if="user.role === 1">新增</el-button>
        </div>
      </div>
      <!--      表格样式-->
      <el-table :data="tableData" border style="width: 100%" :fit="true" stripe :cell-style="{ textAlign: 'center' }" :header-cell-style="{ textAlign: 'center' }">
        <el-table-column prop="userName" label="姓名"></el-table-column>
        <el-table-column prop="phone" label="手机号"></el-table-column>
        <el-table-column prop="age" label="年龄"></el-table-column>
        <el-table-column prop="gender" label="性别"></el-table-column>
        <el-table-column prop="password" label="密码"></el-table-column>
        <el-table-column fixed="right" label="操作">
          <template slot-scope="scope">
<!--            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>-->
            <el-button type="text" size="small" @click="edit(scope.row)" v-if="user.role === 1">编辑</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)" style="margin-left: 10px">
              <el-button size="small" type="text" slot="reference" v-if="user.role === 1">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <!--分页样式-->
      <div class="block" style="text-align: right;margin-top: 50px">
        <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="currentPage4"
            :page-sizes="[100, 200, 300, 400]"
            :page-size="100"
            layout="prev, pager, next"
            :total="1000">
        </el-pagination>
      </div>

    </div>

    <!--    模态框-->
    <el-dialog title="请填写信息" :visible.sync="dialogVisible" width="40%">
      <el-form :model="form" label-position="right" label-width="100px" style="padding-right: 40px">
        <el-form-item label="用户名">
          <el-input size="small" v-model="form.userName" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group size="small" v-model="form.gender">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input size="small" v-model="form.age" placeholder="请输入年龄"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input size="small" v-model="form.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="save">保 存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";//引入接口
export default {
  // 页面加载时调用方法
  created(){
    this.load();
  },
  methods: {
    handleClick(row) {
      console.log(row);
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    load(){//load接口request请求
      request.get("/user/alldata").then(res => {
        //   如果调接口成了
        if (res.code === '0'){//后端Result中我们定义SUCCESS = ‘0’为调用接口成功
          this.tableData = res.data;
        } else {//如果不成功，打印出错误信息
          this.$notify.error(res.msg);//msg是后端定义的属性
        }
      })
    },

    // 点击新增按钮点击事件调用
    add(){
      this.form = {};
      this.dialogVisible = true;
    },
    // 点击编辑，打开模态框，回显当前对象的信息
    edit(row){
      this.dialogVisible = true;
      this.form = row;
    },
    // 点击保存，新增的时候id是空的，判断有没有id，如果没有id的话是新增，如果有id的话 是编辑
    save(){
      if (!this.form.id){ //如果没有id 走新增接口
        this.form.role = 2;//分权限
        request.post("/user",this.form).then(res =>{ //post请求把form对象传到后端，后端写逻辑把form存到数据库里
          if (res.code === '0'){ //如果接口调用成功，则把模态框关闭，重新加载一下数据库的数据
            this.$notify.success("新增成功");
            this.dialogVisible = false;
            this.load();
          } else {
            this.$notify.error(res.msg); // 如果不成功，返回报错信息
          }
        })
      } else {
        request.post("/user/edit",this.form).then(res =>{
          if (res.code === '0'){
            this.$notify.success("修改成功");
            this.dialogVisible = false;
            this.load();
          } else {
            this.$notify.error(res.msg); // 如果不成功，返回报错信息
          }
        })
      }
    },

    // 根据ID删除某条数据
    del(id) {
      request.delete("/user/" + id).then(res => {
        if (res.code === '0') {
          this.$notify.success('删除成功');
          this.load();
        } else {
          this.$notify.error(res.msg);
        }
      });
    },
  },

  data() {
    return {
      // 定义User对象，从缓存里取值
      user: JSON.parse(localStorage.getItem("user") || {}),
      input:'',
      currentPage1: 5,
      currentPage2: 5,
      currentPage3: 5,
      currentPage4: 4,
      tableData: [],
      // 模态框
      dialogVisible:false,
      form:{},
    }
  }
}
</script>