<template>
  <div>

    <el-form :inline="true"   class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="dialogFormVisible = true">添加商品</el-button>
      </el-form-item>
      <el-form-item  >
        <el-input v-model="name"  placeholder="姓名"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="select(name)">查询</el-button>
      </el-form-item>
    </el-form>

    <el-dialog title="增加商品" :visible.sync="dialogFormVisible">
      <el-form :model="bizGoodsForm" :rules="rules" ref="bizGoodsForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="名称" prop="name">
          <el-input v-model="bizGoodsForm.name"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="图片">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:7777/common/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="bizGoodsForm.photos" :src="bizGoodsForm.photos" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>


        <el-form-item label="价格" prop="price">
          <el-input v-model="bizGoodsForm.price" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="库存" prop="stock">
          <el-input v-model="bizGoodsForm.stock" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="类型" prop="type">
          <el-select v-model="bizGoodsForm.type" placeholder="请选择">
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
        <el-button @click="resetForm('bizGoodsForm')">重置</el-button>
        <el-button type="primary" @click="submitForm('bizGoodsForm')">立即创建</el-button>

      </div>
    </el-dialog>

    <el-dialog title="修改商品" :visible.sync="dialogFormVisible2">
      <el-form :model="bizGoodsForm" :rules="rules" ref="bizGoodsForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="名称" prop="name">
          <el-input v-model="bizGoodsForm.name"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="图片">
          <el-upload
            class="avatar-uploader"
            action="http://localhost:7777/common/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
            <img v-if="bizGoodsForm.photos" :src="bizGoodsForm.photos" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>


        <el-form-item label="价格" prop="price">
          <el-input v-model="bizGoodsForm.price" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="库存" prop="stock">
          <el-input v-model="bizGoodsForm.stock" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="类型" prop="type">
          <el-select v-model="bizGoodsForm.type" placeholder="请选择">
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
        <el-button @click="resetForm('bizGoodsForm')">重置</el-button>
        <el-button type="primary" @click="editForm('bizGoodsForm')">立即修改</el-button>

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

      <el-table-column label="图片"  min-width="12%" prop="image">

        <template width="12%" slot-scope="scope">

          <img style="width:80px;height:80px;border:none;" :src="scope.row.photos">

        </template>

      </el-table-column>
      <el-table-column

        prop="price"
        label="价格"
        min-width="12%">
      </el-table-column>
      <el-table-column

        prop="stock"
        label="库存"
        min-width="12%">
      </el-table-column>
      <el-table-column

        prop="type"
        label="类型"
        min-width="12%">
        <template slot-scope="scope">{{options[scope.row.type]['label']}}</template>
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
  name: "BizGoods",
  methods: {
    deleteUser(row){
      const _this = this
      this.$confirm('确定删除该数据?','提示',{
        type:'warning'
      }).then(()=>{
        axios.get('http://localhost:7777/goods/deleteById/'+row.id).then(function (resp){
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
      axios.get('http://localhost:7777/goods/findById/'+ _this.rowid).then(function (resp){
        _this.bizGoodsForm = resp.data.result

      })
      _this.dialogFormVisible2 = true;

    },
    select(name){
      const _this = this
      if (name !== "") {
        axios.get('http://localhost:7777/goods/findByName?name=' + name).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.result

        })
      }else {
        window.location.reload()
      }
    },
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/goods/getGoods/'+currentPage+'/5').then(function (resp){

        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total


      })
    },
    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:7777/goods/add',this.bizGoodsForm).then(function (resp){
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
          axios.put('http://localhost:7777/goods/updateById',this.bizGoodsForm).then(function (resp){
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
      this.bizGoodsForm.photos = response.returnFileUrl

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
    axios.get('http://localhost:7777/goods/getGoods/1/5').then(function (resp){
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
      bizGoodsForm: {
        id: '',
        name: '',
        photos: '',
        price: '',
        stock: '',
        type: '',

      },
      rules: {

        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ],
        photos: [
          { required: true, message: '请输入图片', trigger: 'change' }
        ],
        price: [
          { required: true, message: '请输入价格', trigger: 'change' }
        ],
        stock: [
          { required: true, message: '请输入库存', trigger: 'change' }
        ],
        type: [
          { required: true, message: '请输入类型', trigger: 'change' }
        ]
      },
      options: [
        {
          value: 0,
          label: "未知"
        },
        {
          value: 1,
          label: "水泥"
        },
        {
          value: 2,
          label: "玻璃"
        },
        {
          value: 3,
          label: "石材"
        },
        {
          value: 4,
          label: "涂鸦"
        },
        {
          value: 5,
          label: "塑料"
        },
        {
          value: 6,
          label: "地板"
        },
        {
          value: 7,
          label: "大理石"
        },
        {
          value: 8,
          label: "玻璃钢"
        },
        {
          value: 9,
          label: "装饰板"
        }
      ]
    }
  }
}
</script>
