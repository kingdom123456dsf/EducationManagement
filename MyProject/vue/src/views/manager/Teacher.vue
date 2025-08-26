<template>
  <div>
    <div style="width: 100%;height:1000px;padding:40px 40px; background-color: white;border-radius: 4px">
      <!--按钮 和 搜索框样式-->
      <div style="margin-bottom: 40px;font-size: 18px;font-weight: 530;">教师信息管理</div>
      <div style="margin-bottom: 20px; display: flex">
        <div style="flex: 10;text-align: left">
          <el-input placeholder="请输入教师编号查询" size="small"  v-model="search.tno" style="width: 20%; margin-right: 20px"><i slot="suffix" class="el-input__icon el-icon-search"></i></el-input>
          <el-button type="success" size="small" style="border-radius: 1px;width: 100px;text-align: center" @click="load">查询</el-button>
        </div>
        <div style="flex:2;text-align: right">
          <el-button type="success" size="small" style="border-radius: 1px;width: 100px;text-align: center" @click="add">新增</el-button>
        </div>
      </div>
      <!--      表格样式-->
      <el-table :data="tableData" border :fit="true" stripe style="width: 100%"  :cell-style="{ textAlign: 'center' }" :header-cell-style="{ textAlign: 'center' }"  >
        <el-table-column prop="tno" label="教师编号"></el-table-column>
        <el-table-column prop="tname" label="姓名"></el-table-column>
        <el-table-column prop="tsex" label="性别"></el-table-column>
        <el-table-column prop="tage" label="年龄"></el-table-column>
        <el-table-column prop="tlocation" label="家庭地址"></el-table-column>
        <el-table-column prop="tnation" label="民族"></el-table-column>
        <el-table-column fixed="right" label="操作" >
          <template slot-scope="scope">
<!--            <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>-->
            <el-button type="text" size="small" @click="edit(scope.row)">编辑</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.tno)" style="margin-left: 10px">
              <el-button size="small" type="text" slot="reference">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>

      <!--分页样式-->
      <div class="block" style="text-align: right;margin-top: 50px">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-size="5"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>

    </div>
    <!--    模态框-->
    <el-dialog title="请填写信息" :visible.sync="dialogVisible" width="40%">
      <el-form :model="form" label-position="right" label-width="100px" style="padding-right: 40px">
        <el-form-item label="教师编号">
          <el-input size="small" v-model="form.tno" placeholder="请输入教师编号"></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input size="small" v-model="form.tname" placeholder="请输入教师姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group size="small" v-model="form.tsex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input size="small" v-model="form.tage" placeholder="请输入年龄"></el-input>
        </el-form-item>
        <el-form-item label="家庭地址">
          <el-input size="small" v-model="form.tlocation" placeholder="请输入家庭地址"></el-input>
        </el-form-item>
        <el-form-item label="民族">
          <el-input size="small" v-model="form.tnation" placeholder="请输入民族"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="save">保 存</el-button>
      </div>
    </el-dialog>
    <!--    模态框-->

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

    //  当点击页码时，当前页码数赋值给data变量pageNum
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum;
      this.load();
    },
    // 分页加载表格数据
    load() {
      request.post("/teacher/page?pageNum=" + this.pageNum, this.search).then(res => {
        if (res.code === '0') {
          this.tableData = res.data.list;
          this.total = res.data.total;
        } else {
          this.$notify.error(res.msg);
        }
      });
    },
    /*load() {
      request.get("/teacher/alldata").then(res => {
        if(res.code === '0') {
          this.tableData = res.data;
        }else {
          this.$notify.error(res.msg);
        }
      })
    },*/

    // 点击新增按钮点击事件调用
    add(){
      this.form = {};
      this.dialogVisible = true;
    },
    // 点击编辑，打开模态框，回显当前对象的信息
    edit(row){
      this.dialogVisible = true;
      this.form = row;
      this.form.isEdit = true; // 添加编辑标识
    },
    save() {
      if (!this.form.isEdit) {
        request.post("/teacher", this.form).then(res => {
          if (res.code === '0') {
            this.$notify.success("新增成功");
            this.dialogVisible = false;
            this.load();
          } else {
            this.$notify.error(res.msg);
          }
        })
      } else {
        request.post("/teacher/edit", this.form).then(res => {
          if (res.code === '0') {
            this.$notify.success("修改成功");
            this.dialogVisible = false;
            this.load();
          } else {
            this.$notify.error(res.msg);
          }
        })
      }
    },

    // 根据ID删除某条数据
    del(tno) {
      request.delete("/teacher/" + tno).then(res => {
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
      pageNum:1,
      total:0,
      search:{},
      tableData: [],
      // 模态框
      dialogVisible:false,
      form:{},
    }
  }
}
</script>