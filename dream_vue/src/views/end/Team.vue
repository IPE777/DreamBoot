<template>
  <div>

    <el-form :inline="true"   class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="dialogFormVisible = true">添加团队</el-button>
      </el-form-item>
      <el-form-item  >
        <el-input v-model="name"  placeholder="团队名称"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="select(name)">查询</el-button>
      </el-form-item>
    </el-form>

    <el-dialog title="增加团队" :visible.sync="dialogFormVisible">
      <el-form :model="teamForm" :rules="rules" ref="teamForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="团队名" prop="name">
          <el-input v-model="teamForm.name"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="联系人" prop="username">
          <el-input v-model="teamForm.contact" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input v-model="teamForm.phone" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="地址" prop="address">
          <el-input v-model="teamForm.address" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="执照">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:7777/common/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="teamForm.license" :src="teamForm.license" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="满意度">
          <el-select v-model="teamForm.satisfied" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="订单数" prop="order_sum">
          <el-input v-model="teamForm.order_sum" style="width: 250px"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('teamForm')">重置</el-button>
        <el-button type="primary" @click="submitForm('teamForm')">立即创建</el-button>

      </div>
    </el-dialog>

    <el-dialog title="修改团队" :visible.sync="dialogFormVisible2">
      <el-form :model="teamForm" :rules="rules" ref="teamForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="团队名" prop="name">
          <el-input v-model="teamForm.name"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="联系人" prop="username">
          <el-input v-model="teamForm.contact" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="电话" prop="phone">
          <el-input v-model="teamForm.phone" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="地址" prop="address">
          <el-input v-model="teamForm.address" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="执照">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:7777/common/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="teamForm.license" :src="teamForm.license" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item label="满意度">
          <el-select v-model="teamForm.satisfied" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="订单数" prop="order_sum">
          <el-input v-model="teamForm.order_sum" style="width: 250px"></el-input>
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

        prop="code"
        label="编码"
        min-width="12%">
      </el-table-column>


      <el-table-column

        prop="name"
        label="团队名称"
        min-width="12%">
      </el-table-column>
      <el-table-column

        prop="contact"
        label="联系人"
        min-width="12%">
      </el-table-column>

      <el-table-column

        prop="phone"
        label="电话"
        min-width="12%">
      </el-table-column>

      <el-table-column

        prop="address"
        label="地址"
        min-width="12%">
      </el-table-column>

      <el-table-column label="执照"  min-width="12%" prop="license">

        <template width="12%" slot-scope="scope">

          <img style="width:80px;height:80px;border:none;" :src="scope.row.license">

        </template>

      </el-table-column>

      <el-table-column
        prop="satisfied"
        label="满意度"
        min-width="12%">
        <template slot-scope="scope">{{options[scope.row.satisfied]['label']}}</template>
      </el-table-column>

      <el-table-column
        prop="order_sum"
        label="订单数"
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
  name: "Team",
  methods: {

    deleteUser(row){
      const _this = this
      this.$confirm('确定删除该数据?','提示',{
        type:'warning'
      }).then(()=>{
        axios.get('http://localhost:7777/team/deleteById/'+row.id).then(function (resp){
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
      axios.get('http://localhost:7777/team/findById/'+ _this.rowid).then(function (resp){
        _this.teamForm = resp.data.result

      })
      _this.dialogFormVisible2 = true;

    },
    select(name){
      const _this = this
      if (name !== "") {
        axios.get('http://localhost:7777/team/findByName?name=' + name).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.result

        })
      }else {
        window.location.reload()
      }
    },
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/team/getTeam/'+currentPage+'/5').then(function (resp){

        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total

      })
    },
    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:7777/team/add',this.teamForm).then(function (resp){
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
          axios.put('http://localhost:7777/team/updateById',this.teamForm).then(function (resp){
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
      this.teamForm.license = response.returnFileUrl

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
    axios.get('http://localhost:7777/team/getTeam/1/5').then(function (resp){
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
      teamForm: {
        id: '',
        code: '',
        name: '',
        contact: '',
        phone: '',
        address: '',
        license: '',
        satisfied: '',
        order_sum: ''

      },

      options: [{
        value: 1,
        label: '优'
      },{
        value: 2,
        label: '良'
      },{
        value: "2",
        label: "差"
      }],

      rules: {

        name: [
          { required: true, message: '请输入姓名', trigger: 'change' }
        ]
      }
    }
  }
}
</script>
