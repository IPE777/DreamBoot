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
<!--    <el-divider></el-divider>-->
<!--    <div class="demo-image__placeholder">-->
<!--      <div class="block">-->
<!--        <span class="demonstration">默认</span>-->
<!--        <el-image :src="image"></el-image>-->
<!--      </div>-->

<!--    </div>-->
    <el-main style="height: 893px;width: 1720px;padding-left: 300px" >

      <el-dialog title="增加内容" :visible.sync="dialogFormVisible">
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

<!--          <el-form-item label="标题" prop="title">-->
<!--            <el-input v-model="contentForm.title" style="width: 250px"></el-input>-->
<!--          </el-form-item>-->

          <el-form-item label="内容" prop="content">
            <el-input type="textarea" v-model="contentForm.content" style="width: 250px"></el-input>
          </el-form-item>


<!--          <el-form-item label="点赞数" prop="likes">-->
<!--            <el-input v-model="contentForm.likes" style="width: 250px"></el-input>-->
<!--          </el-form-item>-->

<!--          <el-form-item label="标签">-->
<!--            <el-select v-model="contentForm.tag" placeholder="请选择">-->
<!--              <el-option-->
<!--                v-for="item in options"-->
<!--                :key="item.value"-->
<!--                :label="item.label"-->
<!--                :value="item.value">-->
<!--              </el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->

        </el-form>

        <div slot="footer" class="dialog-footer">
          <el-button @click="resetForm('contentForm')">重置</el-button>
          <el-button type="primary" @click="submitForm('contentForm')">立即创建</el-button>

        </div>
      </el-dialog>

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
          <el-button type="primary" @click="dialogFormVisible = true">发表文章</el-button>
        </el-form-item>



        <el-form-item>
          <el-input v-model="commentsForm.comment"  style="margin-left: 800px;max-width: 372px" placeholder="请输入内容"></el-input>

        </el-form-item>

        <el-form-item>
          <el-button type="primary"  style="margin-left: 160px;width: 100px"    @click="publish('commentsForm')">发表评论</el-button>
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

      <el-form :inline="true"   class="demo-form-inline">
        <el-form-item>
          <span style="font-size: 20px">{{tableData[0]['collect']}}</span>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="addLikes('tableData')">收藏文章</el-button>
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

.el-icon-star-on{
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
  name: "FrontIndex",
  data(){
    return{
      id: '',
      name: '',
      image: '',
      contentId: '',
      dialogFormVisible: false,
      total: null,
      tableData: [{
        id: '',
        customer_name: '123',
        customer_image: 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/10/23/2d16b6ec-1dd0-49e9-8dba-597f9a0769b2.jpg',
        content: '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容',
        photos: 'http://chos7n1.oss-cn-hangzhou.aliyuncs.com/dream/2022/11/02/1ff5b76c-a70e-4ba4-934e-1e20163b60a7.jpg',
        likes: 0,
        collect: 0
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
      dataForm: {
        id: '',
        customer_id: '',
        customer_name: '',
        customer_images: '',
        photos: '',
        content: '',
        likes: '',
        collect: ''

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
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/content/getContent/'+currentPage+'/1').then(function (resp){

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
    publish(formName){
      var _this = this
      _this.commentsForm.content_id = _this.contentId
      _this.commentsForm.customer_id = _this.id
      _this.commentsForm.customer_name = _this.name
      _this.commentsForm.customer_image= _this.image
      // console.log("_this.commentsForm.content_id="+_this.commentsForm.content_id)
      // console.log("_this.commentsForm.customer_id="+_this.commentsForm.customer_id)
      // console.log(" _this.commentsForm.customer_name="+ _this.commentsForm.customer_name)
      // console.log("_this.commentsForm.customer_image="+_this.commentsForm.customer_image)
      // console.log("_this.commentsForm.comment="+_this.commentsForm.comment)


      axios.post('http://localhost:7777/comment/add',this.commentsForm).then(function (resp){
            if(resp.data.result === 'success'){
              console.log(resp)
              _this.commentsForm.comment = ''
              _this.$alert('添加成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  window.location.reload()
                }
              })
            }
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
    submitForm(formName) {
      const _this=this
      _this.contentForm.customer_id = _this.id
      _this.contentForm.customer_name = _this.name
      _this.contentForm.customer_image = _this.image
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:7777/content/add',this.contentForm).then(function (resp){
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
    addLikes(formName){
      var _this = this
      const arr = _this.tableData

      arr.forEach((item, index) => {
        _this.dataForm.id = item.id
        _this.dataForm.customer_id = item.customer_id
        _this.dataForm.customer_name = item.customer_name
        _this.dataForm.customer_images = item.customer_images
        _this.dataForm.photos = item.photos
        _this.dataForm.content = item.content
        _this.dataForm.likes = item.likes
        _this.dataForm.collect = item.collect

        console.log("item=" + item.content)

      })

      axios.post('http://localhost:7777/content/addLikes/'+ _this.id +'/'+_this.contentId,_this.dataForm).then(resp =>{
        if(resp.data.code === '200'){
          _this.$alert('收藏成功', '消息', {
            confirmButtonText: '确定',
            callback: action => {
              window.location.reload()

            }
          })
        }else{
          _this.$alert('您已收藏', '消息', {
            confirmButtonText: '确定',
            callback: action => {

            }
          })
        }
      })
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
          _this.id = resp.data.result.id
          _this.image = resp.data.result.image
          _this.name = resp.data.result.name

      }
    })

    axios.get('http://localhost:7777/content/getContent/1/1').then(function (resp){
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
}
</script>


