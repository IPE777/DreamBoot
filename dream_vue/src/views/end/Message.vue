<template>
  <div>

    <el-form :inline="true"   class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="dialogFormVisible = true">添加消息</el-button>
      </el-form-item>
      <el-form-item  >
        <el-input v-model="name"  placeholder="客户名称"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="select(name)">查询</el-button>
      </el-form-item>
    </el-form>

    <el-dialog title="增加消息" :visible.sync="dialogFormVisible">
      <el-form :model="messageForm" :rules="rules" ref="messageForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="消息" prop="message">
          <el-input v-model="messageForm.message"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="图片" prop="content_photos">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:7777/common/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="messageForm.content_photos" :src="messageForm.content_photos" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="发布主体" prop="customer_name">
          <el-input v-model="messageForm.customer_name" style="width: 250px"></el-input>
        </el-form-item>


      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('messageForm')">重置</el-button>
        <el-button type="primary" @click="submitForm('messageForm')">立即创建</el-button>

      </div>
    </el-dialog>

    <el-dialog title="修改团队" :visible.sync="dialogFormVisible2">
      <el-form :model="messageForm" :rules="rules" ref="messageForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="消息" prop="message">
          <el-input v-model="messageForm.message"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="发布主体" prop="customer_name">
          <el-input v-model="messageForm.customer_name" style="width: 250px"></el-input>
        </el-form-item>





      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('teamForm')">重置</el-button>
        <el-button type="primary" @click="editForm('teamForm')">立即修改</el-button>

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

        prop="customer_id"
        label="客户编号"
        min-width="12%">
      </el-table-column>


      <el-table-column

        prop="customer_name"
        label="客户名称"
        min-width="12%">
      </el-table-column>
      <el-table-column

        prop="master_id"
        label="原主id"
        min-width="12%">
      </el-table-column>
      <el-table-column

        prop="content_id"
        label="文章id"
        min-width="12%">
      </el-table-column>

<!--      <el-table-column-->

<!--        prop="content_name"-->
<!--        label="文章内容"-->
<!--        min-width="12%">-->
<!--      </el-table-column>-->

      <el-table-column label="文章图片"  min-width="12%" prop="license">

        <template width="12%" slot-scope="scope">

          <img style="width:80px;height:80px;border:none;" :src="scope.row.content_photos">

        </template>

      </el-table-column>

      <el-table-column

        prop="message"
        label="消息内容"
        min-width="12%">
      </el-table-column>

      <el-table-column

        prop="type"
        label="消息类型"
        min-width="12%">
        <template slot-scope="scope">{{options[scope.row.type]['label']}}</template>
      </el-table-column>

      <el-table-column

        prop="create_time"
        label="时间"
        min-width="12%">
      </el-table-column>

      <el-table-column
        fixed="right"
        label="操作"
        width="100">
        <template slot-scope="scope">
<!--          <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>-->
          <el-button @click="deleteMessage(scope.row)" type="text" size="small">删除</el-button>
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
  name: "Message",
  methods: {

    deleteMessage(row){
      const _this = this
      this.$confirm('确定删除该数据?','提示',{
        type:'warning'
      }).then(()=>{
        axios.get('http://localhost:7777/message/deleteById/'+row.id).then(function (resp){
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
      const _this = this
      _this.rowid = row.id;
      console.log(_this.rowid)
      axios.get('http://localhost:7777/message/findById/'+ _this.rowid).then(function (resp){
        _this.messageForm = resp.data.result

      })
      _this.dialogFormVisible2 = true;

    },
    select(name){
      const _this = this
      if (name !== "") {
        axios.get('http://localhost:7777/message/findByName?customer_name=' + name).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.result

        })
      }else {
        window.location.reload()
      }
    },
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/message/getMessage/'+currentPage+'/5').then(function (resp){

        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total

      })
    },
    submitForm(formName) {
      const _this=this
      _this.messageForm.type = 1
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:7777/message/add',this.messageForm).then(function (resp){
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
          axios.put('http://localhost:7777/message/updateById',this.messageForm).then(function (resp){
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
    },
    handleAvatarSuccess(response, file) {
      console.log(file.name)
      console.log("图片路径=" + response.returnFileUrl)
      this.messageForm.content_photos = response.returnFileUrl

    },

    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }

      return isJPG && isLt2M;
    }
  },
  created() {
    const _this = this
    axios.get('http://localhost:7777/message/getMessage/1/5').then(function (resp){
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
      dialogFormVisible2: false,
      name: '',
      messageForm: {
        id: '',
        customer_id: '',
        customer_name: '',
        content_id: '',
        content_photos: '',
        message: '',
        type: ''
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

        customer_name: [
          { required: true, message: '请输入姓名', trigger: 'change' }
        ],
        content_photos: [
          { required: true, message: '请输入图片', trigger: 'change' }
        ],
        message: [
          { required: true, message: '请输入消息', trigger: 'change' }
        ]
      }
    }
  }
}
</script>
