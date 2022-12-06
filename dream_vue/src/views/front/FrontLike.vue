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
        default-active="/front/like"
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

      <el-main style="height: 875px;width: 1720px;padding-left: 300px">
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
                    <img style="width:50px;height:50px;border:none;" :src="array.customer_image">
                  </template>
                </el-table-column>

                <el-table-column width="70px" prop="customer_name" >
                  {{array.customer_name}}
                </el-table-column>

                <el-table-column width="145px" prop="content" >
                  {{array.content}}
                </el-table-column>

              </el-table>
            </template>

          </el-table-column>

          <el-table-column width="600px" prop="photos" >

            <template  slot-scope="scope" >

              <img style="width:600px;height:600px;border:none;" :src="array.photos">

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
            <template slot-scope="scope">
              <el-button type="primary" @click="want('orderForm')">意向装修</el-button>
            </template>
          </el-form-item>

          <el-form-item>
            <el-input v-model="commentsForm.comment"  style="margin-left: 795px;max-width: 370px" placeholder="请输入内容"></el-input>

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

        <el-form :inline="true"   class="demo-form-inline">
          <el-form-item>
            <span style="font-size: 20px">{{array.collect}}</span>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="deleteLikes('tableData')">取消收藏</el-button>
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
  name: "FrontLike",
  data(){
    return{
      id: '',
      name: '',
      image: '',
      contentId: '',
      pages: 0,
      total: null,
      dialogFormVisible: false,
      array:{

      },
      tableData: [{
        id: '',
        customer_name: '',
        customer_image: '',
        content: '',
        photos: '',
      }],
      commentData: [{
        id: '',
        content_id: '',
        customer_name: '',
        customer_image: '',
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
      orderForm: {
        customer_id: '',
        customer_name: '',
        customer_phone: '',
        customer_address: '',
        photos: '',
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
      _this.pages = currentPage-1
      axios.get('http://localhost:7777/content/findByCollecterId/'+_this.pages+'?collecterId='+ _this.id).then(function (resp){

        _this.tableData[0] = resp.data.result.list
        _this.total = resp.data.result.total
        _this.array = resp.data.result.list
        _this.orderForm.photos = resp.data.result.list.photos
        // console.log( " _this.array.collect="+  _this.array.collect)
        // console.log( " _this.array.id="+  _this.array.id)
        // console.log( " _this.array.content="+  _this.array.content)
        const arr = _this.tableData
        console.log("arr="+arr)
        arr.forEach((item, index) => {

          _this.contentId = item.id
          console.log("contentId=" + _this.contentId)
          console.log("item.content=" + item.content)
        })

        axios.get('http://localhost:7777/comment/findByContentId?contentId='+ _this.contentId ).then(function (resp){
          _this.commentData = resp.data.result
          console.log( "_this.contentId="+_this.contentId)
        })
      })

    },
    deleteLikes(formName){
      var _this = this
      console.log(_this.array)

      axios.post('http://localhost:7777/content/deleteLikes/'+ _this.id +'/'+_this.contentId,_this.array).then(resp =>{
        if(resp.data.code === '200'){
          _this.$alert('取消成功', '消息', {
            confirmButtonText: '确定',
            callback: action => {
              window.location.reload()

            }
          })
        }else{ //这里加不加不无所谓 因为只有收藏过才会显示自己的收藏列表 这个应该加在首页
          _this.$alert('您未收藏', '消息', {
            confirmButtonText: '确定',
            callback: action => {

            }
          })
        }
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
    want(formName){
      const _this = this
      axios.post('http://localhost:7777/order/add', _this.orderForm ).then(function (resp){
        if(resp.data.result === 'success'){
          console.log(resp)

          _this.$alert('已收到您的请求', '消息', {
            confirmButtonText: '确定',
            callback: action => {

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

        _this.orderForm.customer_id = resp.data.result.id
        _this.orderForm.customer_name = resp.data.result.name
        _this.orderForm.customer_phone = resp.data.result.phone
        _this.orderForm.customer_address = resp.data.result.address

        console.log( _this.id)

        axios.get('http://localhost:7777/content/findByCollecterId/0?collecterId=' + _this.id).then(function (resp){
          _this.tableData[0] = resp.data.result.list
          _this.array = resp.data.result.list
          _this.total = resp.data.result.total
          _this.orderForm.photos = resp.data.result.list.photos
          const arr = _this.tableData

          console.log("total="+ _this.total)
          console.log("_this.array="+ JSON.stringify(_this.array))


          arr.forEach((item, index) => {
            _this.contentId = item.id
            console.log("contentId=" + _this.contentId)
            console.log("item.content=" + item.content)
          })

          axios.get('http://localhost:7777/comment/findByContentId?contentId='+ _this.contentId ).then(function (resp){
            _this.commentData = resp.data.result

            console.log( "_this.contentId="+_this.contentId)

          })



        })


      }
    })


  }
}
</script>


