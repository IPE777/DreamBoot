<template>
  <div>

    <el-form :inline="true"   class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="dialogFormVisible = true">添加员工</el-button>
      </el-form-item>
      <el-form-item  >
        <el-input v-model="name"  placeholder="姓名"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="select(name)">查询</el-button>
      </el-form-item>

    </el-form>

    <el-dialog title="增加用户" :visible.sync="dialogFormVisible">

      <el-form :model="userForm" :rules="rules" ref="userForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="姓名" prop="name">
          <el-input v-model="userForm.name"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="照片">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:7777/common/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="userForm.image" :src="userForm.image" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>


        <el-form-item label="账号" prop="username">
          <el-input v-model="userForm.username" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="userForm.password" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input v-model="userForm.phone" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
          <el-input v-model="userForm.email" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="出生年月" prop="birthday">
          <div class="block">

            <el-date-picker
              v-model="userForm.birthday"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </div>
        </el-form-item>



        <el-form-item label="性别">
          <el-select v-model="userForm.sex" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>



      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('userForm')">重置</el-button>
        <el-button type="primary" @click="submitForm('userForm')">立即创建</el-button>

      </div>

    </el-dialog>

    <el-dialog title="修改用户" :visible.sync="dialogFormVisible2">

      <el-form :model="userForm" :rules="rules" ref="userForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="name" prop="name">
          <el-input v-model="userForm.name"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="照片">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:7777/common/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="userForm.image" :src="userForm.image" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>


        <el-form-item label="账号" prop="username">
          <el-input v-model="userForm.username" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="密码" prop="password">
          <el-input v-model="userForm.password" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input v-model="userForm.phone" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="邮箱" prop="email">
          <el-input v-model="userForm.email" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="出生年月" prop="birthday">
          <div class="block">

            <el-date-picker
              v-model="userForm.birthday"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择日期">
            </el-date-picker>
          </div>
        </el-form-item>



        <el-form-item label="性别">
          <el-select v-model="userForm.sex" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>



      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('userForm')">重置</el-button>
        <el-button type="primary" @click="editForm('userForm')">立即修改</el-button>

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
        label="姓名"
        min-width="12%">
      </el-table-column>

      <el-table-column label="头像"  min-width="12%" prop="image">

        <template width="12%" slot-scope="scope">

          <img style="width:80px;height:80px;border:none;" :src="scope.row.image">

        </template>

      </el-table-column>
      <el-table-column

        prop="username"
        label="账号"
        min-width="12%">
      </el-table-column>
      <el-table-column

        prop="password"
        label="密码"
        min-width="12%">
      </el-table-column>
      <el-table-column

        prop="phone"
        label="电话"
        min-width="12%">
      </el-table-column>
      <el-table-column

        prop="email"
        label="邮箱"
        min-width="12%">
      </el-table-column>

      <el-table-column
        prop="birthday"
        label="生日"
        min-width="12%">
      </el-table-column>
      <el-table-column
        prop="sex"
        label="性别"
        min-width="12%">
        <template slot-scope="scope">{{options[scope.row.sex]['label']}}</template>
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
  name: "User",
  methods: {
    deleteUser(row){
      const _this = this
      this.$confirm('确定删除该数据?','提示',{
        type:'warning'
      }).then(()=>{
        axios.get('http://localhost:7777/user/deleteById/'+row.id).then(function (resp){
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
      axios.get('http://localhost:7777/user/findById/'+ _this.rowid).then(function (resp){
        _this.userForm = resp.data.result

      })
      _this.dialogFormVisible2 = true;

    },
    select(name){
      const _this = this
      if (name !== "") {
        axios.get('http://localhost:7777/user/findByName?name=' + name).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.result

        })
      }else {
        window.location.reload()
      }
    },
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/user/getUser/'+currentPage+'/5').then(function (resp){

        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total


      })
    },
    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:7777/end/api/register',this.userForm).then(function (resp){
            if(resp.data.code === '200'){
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
          axios.put('http://localhost:7777/user/updateById',this.userForm).then(function (resp){
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
      this.userForm.image = response.returnFileUrl

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
    axios.get('http://localhost:7777/user/getUser/1/5').then(function (resp){
      _this.tableData = resp.data.result.records

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
      userForm: {
        id: '',
        name: '',
        image: '',
        username: '',
        password: '',
        phone: '',
        email: '',
        birthday: '',
        sex: ''

      },
      rules: {

        name: [
          { required: true, message: '请输入姓名', trigger: 'change' }
        ],
        username: [
          { required: true, message: '请输入账号', trigger: 'change' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'change' }
        ],
        phone: [
          { required: true, message: '请输入电话', trigger: 'change' }
        ],
        email: [
          { required: true, message: '请输入邮箱', trigger: 'change' }
        ],
        birthday: [
          { required: true, message: '请输入生日', trigger: 'change' }
        ]


      },
      options: [
        {
          value: 0,
          label: "未知"
        },
        {
          value: 1,
          label: "男"
        },
        {
          value: 2,
          label: "女"
        }
      ]
    }
  }
}
</script>
