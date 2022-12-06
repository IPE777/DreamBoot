<template>
  <div>

    <el-form :inline="true"   class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="dialogFormVisible = true">添加评论</el-button>
      </el-form-item>
      <el-form-item  >
        <el-input v-model="name"  placeholder="用户名称"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="select(name)">查询</el-button>
      </el-form-item>
    </el-form>

    <el-dialog title="增加评论" :visible.sync="dialogFormVisible">
      <el-form :model="commentForm" :rules="rules" ref="commentForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="用户名称" prop="customer_name">
          <el-input v-model="commentForm.customer_name"  style="width: 250px"></el-input>
        </el-form-item>


        <el-form-item label="评论" prop="comment">
          <el-input v-model="commentForm.comment" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="点赞数" prop="likes">
          <el-input v-model="commentForm.likes" style="width: 250px"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('commentForm')">重置</el-button>
        <el-button type="primary" @click="submitForm('commentForm')">立即创建</el-button>

      </div>
    </el-dialog>

    <el-dialog title="修改评论" :visible.sync="dialogFormVisible2">
      <el-form :model="commentForm" :rules="rules" ref="commentForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="用户名称" prop="customer_name">
          <el-input v-model="commentForm.customer_name"  style="width: 250px"></el-input>
        </el-form-item>


        <el-form-item label="评论" prop="comment">
          <el-input v-model="commentForm.comment" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="点赞数" prop="likes">
          <el-input v-model="commentForm.likes" style="width: 250px"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('commentForm')">重置</el-button>
        <el-button type="primary" @click="editForm('commentForm')">立即修改</el-button>

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

        prop="customer_name"
        label="用户名称"
        min-width="12%">
      </el-table-column>


      <el-table-column
        prop="comment"
        label="评论"
        min-width="12%">
      </el-table-column>

      <el-table-column
        prop="likes"
        label="点赞数"
        min-width="12%">
      </el-table-column>


      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>
          <el-button @click="deleteUser(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
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


<script>
import axios from "axios";

export default {
  name: "Comment",
  methods: {

    deleteUser(row){
      const _this = this
      this.$confirm('确定删除该数据?','提示',{
        type:'warning'
      }).then(()=>{
        axios.get('http://localhost:7777/comment/deleteById/'+row.id).then(function (resp){
          if (resp.data.code === "200") {
            alert("删除成功")
            window.location.reload()
          } else {
            alert("删除失败")
          }
        }).catch((error) => {
          console.log("删除失败")
        })
      })
    },
    edit(row) {
      this.$router.push({
        path: '/commentUpdate',
        query:{
          id: row.id
        }
      })
    },
    select(name){
      const _this = this
      if (name !== "") {
        axios.get('http://localhost:7777/comment/findByName?name=' + name).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.result

        })
      }else {
        window.location.reload()
      }
    },
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/comment/getComment/'+currentPage+'/5').then(function (resp){

        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total

      })
    },
    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:7777/comment/add',this.commentForm).then(function (resp){
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
    editForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.put('http://localhost:7777/comment/updateById',this.commentForm).then(function (resp){
            if(resp.data.result === 'success'){
              _this.$alert('修改成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.dialogFormVisible2 = false
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
    axios.get('http://localhost:7777/comment/getComment/1/5').then(function (resp){
      _this.tableData = resp.data.result.records
      console.log(_this.tableData)
      _this.total = resp.data.result.total

    })

  },

  data() {
    return {
      total: null,
      tableData: null,
      rowid: '',
      dialogFormVisible: false,
      dialogFormVisible2: false,
      name: '',
      commentForm: {
        id: '',
        customer_name: '',
        comment: '',
        likes: ''

      },

      rules: {

        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ]
      }
    }
  }
}
</script>
