<template>
  <div>

    <el-form :inline="true"   class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="dialogFormVisible = true">添加订单</el-button>
      </el-form-item>
      <el-form-item  >
        <el-input v-model="name"  placeholder="客户名称"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="select(name)">查询</el-button>
      </el-form-item>
    </el-form>

    <el-dialog title="增加订单" :visible.sync="dialogFormVisible">
      <el-form :model="bizGoodsOrderForm" :rules="rules" ref="bizGoodsOrderForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="客户名称" prop="customer_name">
          <el-input v-model="bizGoodsOrderForm.customer_name"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="客户地址" prop="customer_address">
          <el-input v-model="bizGoodsOrderForm.customer_address"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="商品名称" prop="goods_name">
          <el-input v-model="bizGoodsOrderForm.goods_name" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="数量" prop="count">
          <el-input v-model="bizGoodsOrderForm.count" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="单价" prop="price">
          <el-input v-model="bizGoodsOrderForm.price" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="总价" prop="sum">
          <el-input v-model="bizGoodsOrderForm.sum" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="状态">
          <el-select v-model="bizGoodsOrderForm.status" placeholder="请选择">
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
        <el-button @click="resetForm('bizGoodsOrderForm')">重置</el-button>
        <el-button type="primary" @click="submitForm('bizGoodsOrderForm')">立即创建</el-button>

      </div>
    </el-dialog>

    <el-dialog title="修改订单" :visible.sync="dialogFormVisible2">
      <el-form :model="bizGoodsOrderForm" :rules="rules" ref="bizGoodsOrderForm" label-width="100px" class="demo-ruleForm">


        <el-form-item label="编码" prop="code">
          <el-input v-model="bizGoodsOrderForm.code"  style="width: 250px"  :disabled="true"></el-input>
        </el-form-item>

        <el-form-item label="客户名称" prop="customer_name">
          <el-input v-model="bizGoodsOrderForm.customer_name"  style="width: 250px"  :disabled="true"></el-input>
        </el-form-item>

        <el-form-item label="客户地址" prop="customer_address">
          <el-input v-model="bizGoodsOrderForm.customer_address"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="商品名称" prop="goods_name">
          <el-input v-model="bizGoodsOrderForm.goods_name" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="数量" prop="count">
          <el-input v-model="bizGoodsOrderForm.count" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="单价" prop="price">
          <el-input v-model="bizGoodsOrderForm.price" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="总价" prop="sum">
          <el-input v-model="bizGoodsOrderForm.sum" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="状态">
          <el-select v-model="bizGoodsOrderForm.status" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="创建时间" prop="sum">
          <el-input v-model="bizGoodsOrderForm.create_time" style="width: 250px"></el-input>
        </el-form-item>

      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="resetForm('bizGoodsOrderForm')">重置</el-button>
        <el-button type="primary" @click="editForm('bizGoodsOrderForm')">立即修改</el-button>

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
        min-width="10%">
      </el-table-column>


      <el-table-column

        prop="customer_name"
        label="客户名称"
        min-width="10%">
      </el-table-column>


      <el-table-column

        prop="customer_address"
        label="客户地址"
        min-width="10%">
      </el-table-column>
      <el-table-column

        prop="goods_name"
        label="商品名称"
        min-width="10%">
      </el-table-column>
      <el-table-column

        prop="count"
        label="数量"
        min-width="10%">
      </el-table-column>
      <el-table-column

        prop="price"
        label="单价"
        min-width="10%">
      </el-table-column>

      <el-table-column
        prop="sum"
        label="总额"
        min-width="10%">
      </el-table-column>
      <el-table-column
        prop="status"
        label="状态"
        min-width="12%">
        <template slot-scope="scope">{{options[scope.row.status]['label']}}</template>
      </el-table-column>
      <el-table-column
        prop="goods_code"
        label="商品编号"
        min-width="10%">
      </el-table-column>
      <el-table-column
        prop="create_time"
        label="创建时间"
        min-width="10%">
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
  methods: {

    deleteUser(row){
      const _this = this
      this.$confirm('确定删除该数据?','提示',{
        type:'warning'
      }).then(()=>{
        axios.get('http://localhost:7777/goodsOrder/deleteById/'+row.id).then(function (resp){
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
      axios.get('http://localhost:7777/goodsOrder/findById/'+ _this.rowid).then(function (resp){
        _this.bizGoodsOrderForm = resp.data.result

      })
      _this.dialogFormVisible2 = true;

    },
    select(name){
      const _this = this
      if (name !== "") {
        axios.get('http://localhost:7777/goodsOrder/findByName?name=' + name).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.result

        })
      }else {
        window.location.reload()
      }
    },
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/goodsOrder/getGoodsOrder/'+currentPage+'/5').then(function (resp){

        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total


      })
    },
    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:7777/goodsOrder/add',this.bizGoodsOrderForm).then(function (resp){
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
          axios.put('http://localhost:7777/goodsOrder/updateById',this.bizGoodsOrderForm).then(function (resp){
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
    axios.get('http://localhost:7777/goodsOrder/getGoodsOrder/1/5').then(function (resp){
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
      bizGoodsOrderForm: {
        id: '',
        code: '',
        customer_id: '',
        customer_name: '',
        customer_address: '',
        goods_id: '',
        goods_name: '',
        goods_code: '',
        count: '',
        price: '',
        sum: '',
        status: '',

      },
      options: [
        {
          value: 0,
          label: "未处理"
        },
        {
          value: 1,
          label: "处理中"
        },
        {
          value: 2,
          label: "已处理"
        }
      ],

      rules: {

        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ],
        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ],
        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ],
        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ],
        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ],
        name: [
          { required: true, message: '请输入名称', trigger: 'change' }
        ]
      }
    }
  }
}
</script>
