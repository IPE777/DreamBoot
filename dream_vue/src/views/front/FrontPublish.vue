<template>
  <div>
    <el-container style="background: #b8b8d3">
      <el-header style="background: #e3e3e5">
        <el-avatar :src="image" style="float: left"></el-avatar>  <span class="title" style="float: left">{{name}}</span>
        <i class="el-icon-switch-button" v-on:click="logout" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>
        <i class="el-icon-user-solid" v-on:click="toPerson" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>
        <i class="el-icon-message-solid" v-on:click="toMessage" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>
        <i class="el-icon-s-shop" v-on:click="toShop" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>
        <i class="el-icon-s-promotion" v-on:click="toIndex" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>

      </el-header>

      <!--    <div class="demo-image__placeholder">-->
      <!--      <div class="block">-->
      <!--        <span class="demonstration">默认</span>-->
      <!--        <el-image :src="image"></el-image>-->
      <!--      </div>-->

      <!--    </div>-->

      <div class="line"></div>
      <el-menu
        router
        default-active="/front/publish"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        style="text-align: center;width: 100%">
        <el-menu-item index="/front/person" style="width: 25%">个人信息</el-menu-item>
        <el-menu-item index="/front/publish" style="width: 25%">我的发布</el-menu-item>
        <el-menu-item index="/front/like" style="width: 25%">我的喜欢</el-menu-item>
        <el-menu-item index="/front/business" style="width: 25%">我的商城</el-menu-item>

      </el-menu>


      <el-dialog title="修改内容" :visible.sync="dialogFormVisible">
        <el-form :model="contentForm" :rules="rules" ref="contentForm" label-width="100px" class="demo-ruleForm">

          <el-form-item label="图片">
            <el-upload
              class="avatar-uploader"
              action="http://localhost:7777/common/upload"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
              <img v-if="contentForm.photos" :src="contentForm.photos" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </el-form-item>

          <el-form-item label="内容" prop="content">
            <el-input type="textarea" v-model="contentForm.content" style="width: 250px"></el-input>
          </el-form-item>


        </el-form>


        <div slot="footer" class="dialog-footer">
          <el-button @click="resetForm('contentForm')">重置</el-button>
          <el-button type="primary" @click="editForm('contentForm')">立即修改</el-button>

        </div>
      </el-dialog>

      <el-main style="height: 832px;width: 1720px;padding-left: 300px">
        <el-table
          :data="tableData"
          width="600px"
          :show-header="false">
          <el-table-column width="300px" prop="content" >

            <template  slot-scope="scope" >
              <el-table
                :data="tableData"
                width="300px"
                height="600px"
                :show-header="false">

                <el-table-column width="60px" height="60px" prop="image" >
                  <template  slot-scope="scope" >
                    <img style="width:50px;height:50px;border:none;" :src="scope.row.customer_image">
                  </template>
                </el-table-column>

                <el-table-column width="70px" prop="customer_name" >

                </el-table-column>

                <el-table-column width="145px" prop="content" >

                </el-table-column>

              </el-table>
            </template>

          </el-table-column>

          <el-table-column width="600px" prop="photos" >

            <template  slot-scope="scope" >

              <img style="width:600px;height:600px;border:none;" :src="scope.row.photos">

            </template>

          </el-table-column>

          <el-table-column width="500px"  >

            <template  slot-scope="scope" >
              <el-table
                :data="commentData"
                width="500px"
                height="600px"
                :show-header="false">

                <el-table-column width="60px" height="60px" prop="image" >
                  <template  slot-scope="scope" >
                    <img style="width:50px;height:50px;border:none;" :src="scope.row.customer_image">
                  </template>
                </el-table-column>

                <el-table-column width="70px" prop="customer_name" >

                </el-table-column>

                <el-table-column width="345px" prop="comment" >

                </el-table-column>

              </el-table>
            </template>

          </el-table-column>

        </el-table>

        <el-form :inline="true"   class="demo-form-inline">

          <el-form-item>
            <el-button type="primary" @click="deleteContent" >删除文章</el-button>
          </el-form-item>

          <el-form-item>
            <template slot-scope="scope">
            <el-button type="primary" @click="edit">修改文章</el-button>
            </template>
          </el-form-item>

          <el-form-item>
            <el-input v-model="commentsForm.comment"  style="margin-left: 685px;max-width: 370px" placeholder="请输入内容"></el-input>

          </el-form-item>

          <el-form-item>
            <el-button type="primary"  style="margin-left: 158px;width: 100px"  @click="publish('commentsForm')">发表评论</el-button>
          </el-form-item>
        </el-form>

        <el-form :inline="true"   class="demo-form-inline">

          <el-form-item>
            <el-pagination
              background
              layout="prev, pager, next"
              page-size="1"
              pager-count="5"
              :total=total
              style="padding-left: 430px;padding-top: 0px"
              @current-change="page">
            </el-pagination>
          </el-form-item>

        </el-form>
      </el-main>

    </el-container>
  </div>
</template>
<style>
.el-icon-switch-button {
  cursor: pointer;
  outline:0;
}

.el-icon-user-solid{
  cursor: pointer;
  outline:0;
}
.el-icon-message-solid{
  cursor: pointer;
  outline:0;
}

.el-icon-s-promotion{
  cursor: pointer;
  outline:0;
}
.el-icon-s-shop{
  cursor: pointer;
  outline:0;
}
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
.el-header{
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
</style>
<script>
import axios from "axios";

export default {
  name: "FrontPublish",
  data(){
    return{
      id: '',
      name: '',
      image: '',
      contentId: '',
      total: null,
      dialogFormVisible: false,

      tableData: [{
        id: '',
        customer_name: '123',
        customer_image: 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/10/23/2d16b6ec-1dd0-49e9-8dba-597f9a0769b2.jpg',
        content: '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容',
        photos: 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/02/1ff5b76c-a70e-4ba4-934e-1e20163b60a7.jpg',
      }],
      commentData: [{
        id: '',
        content_id: '',
        customer_name: '用户1',
        customer_image: 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/10/16/ab4e930a-c312-4cba-a4f9-d6cc5f79db86.jpg',
        comment: '评论'

      }],
      commentsForm: {
        content_id: '',
        customer_id: '',
        customer_name: '',
        customer_image: '',
        comment: ''

      },
      contentForm: {
        customer_id: '',
        customer_name: '',
        customer_image: '',
        photos: '',
        content: ''
      },
      rules: {

        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ]
      }
    }
  },
  methods: {
    logout () {
      var _this = this
      this.$confirm('确定退出吗?','提示',{
        type:'warning'
      }).then(()=>{
        axios.get('http://localhost:7777/front/api/logout').then(resp => {
          console.log(resp.data.code)
          if (resp.data.code === '200') {
            // 前后端状态保持一致
            _this.$store.commit('logout')
            _this.$router.replace('/front/login')
          }
        })
      })
    },
    publish(formName){
      var _this = this
      _this.commentsForm.content_id = _this.contentId
      _this.commentsForm.customer_id = _this.id
      _this.commentsForm.customer_name = _this.name
      _this.commentsForm.customer_image= _this.image
      console.log("_this.commentsForm.content_id="+_this.commentsForm.content_id)
      console.log("_this.commentsForm.customer_id="+_this.commentsForm.customer_id)
      console.log(" _this.commentsForm.customer_name="+ _this.commentsForm.customer_name)
      console.log("_this.commentsForm.customer_image="+_this.commentsForm.customer_image)
      console.log("_this.commentsForm.comment="+_this.commentsForm.comment)


      axios.post('http://localhost:7777/comment/add',this.commentsForm).then(function (resp){
        if(resp.data.result === 'success'){
          console.log(resp)
          _this.commentsForm.comment = ''
          _this.$alert('添加成功', '消息', {
            confirmButtonText: '确定',
            callback: action => {

            }
          })
        }
      })
    },
    edit(){
      const _this = this

      console.log("_this.contentId"+_this.contentId)

      axios.get('http://localhost:7777/content/findById/'+ _this.contentId).then(function (resp){
        _this.contentForm = resp.data.result

      })
      _this.dialogFormVisible = true;
    },
    editForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.put('http://localhost:7777/content/updateById',this.contentForm).then(function (resp){
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
    deleteContent(){
      const _this = this
      this.$confirm('确定删除该数据?','提示',{
        type:'warning'
      }).then(()=>{
        axios.get('http://localhost:7777/content/deleteById/'+ _this.contentId).then(function (resp){
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
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/customer/getContent/'+currentPage+'/1'+'?customerId='+ _this.id).then(function (resp){

        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total

        const arr = _this.tableData
        console.log("arr="+arr)
        arr.forEach((item, index) => {

          _this.contentId = item.id
          console.log("commentId=" + _this.contentId)
        })

        axios.get('http://localhost:7777/comment/findByContentId?contentId='+ _this.contentId ).then(function (resp){
          _this.commentData = resp.data.result
          console.log( "_this.contentId="+_this.contentId)
        })
      })

    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleAvatarSuccess(response, file) {
      console.log(file.name)
      console.log("图片路径=" + response.returnFileUrl)
      this.contentForm.photos = response.returnFileUrl

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
    },
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    toPerson(){
      var _this = this
      _this.$router.push('/front/publish')
    },
    toIndex(){
      var _this = this
      _this.$router.push('/front/index')
    },
    toMessage(){
      var _this = this
      _this.$router.push('/front/message')
    },
    toShop(){
      var _this = this
      _this.$router.push('/front/shop')
    }

  },
  created() {
    console.log( window.localStorage.getItem('user'))
    var _this = this
    axios.get('http://localhost:7777/front/api/getUser?username=' + window.localStorage.getItem('user')).then(resp =>{
      if(resp.data.code === '200'){
        _this.image = resp.data.result.image
        _this.name = resp.data.result.name
        _this.id = resp.data.result.id

        console.log(resp.data.result.image)

        axios.get('http://localhost:7777/customer/getContent/1/1?customerId=' + _this.id).then(function (resp){
          _this.tableData = resp.data.result.records
          _this.total = resp.data.result.total
          const arr = _this.tableData
          console.log(arr)

          arr.forEach((item, index) => {
            _this.contentId = item.id
            console.log("commentId=" + _this.contentId)
          })

          axios.get('http://localhost:7777/comment/findByContentId?contentId='+ _this.contentId ).then(function (resp){
            _this.commentData = resp.data.result
            console.log( "_this.commentId="+_this.contentId)

          })
        })


      }
    })



  }
}
</script>


