<template>
  <div>

    <el-form :inline="true"   class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="dialogFormVisible = true">添加消息</el-button>
      </el-form-item>
      <el-form-item  >
        <el-input v-model="name"  placeholder="名称"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="select(name)">查询</el-button>
      </el-form-item>
    </el-form>

    <el-dialog title="增加词汇" :visible.sync="dialogFormVisible">
      <el-form :model="sensitiveForm" :rules="rules" ref="sensitiveForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="名称" prop="name">
          <el-input v-model="sensitiveForm.name"  style="width: 250px"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('sensitiveForm')">重置</el-button>
        <el-button type="primary" @click="submitForm('sensitiveForm')">立即创建</el-button>

      </div>
    </el-dialog>




    <el-table
      :data="tableData"
      border
      style="width: 100%">

      <el-table-column

        prop="id"
        label="编号"
        min-width="10%">
      </el-table-column>
      <el-table-column

        prop="name"
        label="名称"
        min-width="12%">
      </el-table-column>




<!--      <el-table-column-->
<!--        fixed="right"-->
<!--        label="操作"-->
<!--        width="100">-->
<!--        <template slot-scope="scope">-->
<!--          &lt;!&ndash;          <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>&ndash;&gt;-->
<!--&lt;!&ndash;          <el-button @click="deleteMessage(scope.row)" type="text" size="small">删除</el-button>&ndash;&gt;-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <el-pagination
      background
      layout="prev, pager, next"
      page-size="5"
      :total=total
      @current-change="page">
    </el-pagination>

  </div>
</template>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
<script>
import axios from "axios";

export default {
  name: "Sensitive",
  methods: {

    select(name){
      const _this = this
      if (name !== "") {
        axios.get('http://localhost:7777/sensitive/findByName?name=' + name).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.result

        })
      }else {
        window.location.reload()
      }
    },
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/sensitive/getSensitive/'+currentPage+'/5').then(function (resp){

        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total

      })
    },
    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:7777/sensitive/add',this.sensitiveForm).then(function (resp){
            if(resp.data.result === 'success'){
              console.log(resp)
              _this.$alert('添加成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.dialogFormVisible = false
                  window.location.reload()
                }
              })
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },

    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:7777/sensitive/getSensitive/1/5').then(function (resp){
      _this.tableData = resp.data.result.records
      _this.total = resp.data.result.total
      console.log( " _this.total="+ _this.total)

    })

  },

  data() {
    return {
      total: null,
      tableData: null,
      rowid: '',
      dialogFormVisible: false,
      name: '',
      sensitiveForm: {
        id: '',
        name: '',
      },
      options: [
        {
          value: 0,
          label: "用户"
        },
        {
          value: 1,
          label: "后台"
        }
      ],


      rules: {

        name: [
          { required: true, message: '请输入', trigger: 'change' }
        ]
      }
    }
  }
}
</script>
