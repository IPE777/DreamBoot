<template>
  <el-form :model="bizGoodsOrderForm" :rules="rules" ref="bizGoodsOrderForm" label-width="100px" class="demo-ruleForm">


    <el-form-item label="编码" prop="code">
      <el-input v-model="bizGoodsOrderForm.code"  style="width: 250px"  :disabled="true"></el-input>
    </el-form-item>

    <el-form-item label="客户名称" prop="customer_name">
      <el-input v-model="bizGoodsOrderForm.customer_name"  style="width: 250px"  :disabled="true"></el-input>
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

    <el-form-item>
      <el-button type="primary" @click="submitForm('bizGoodsOrderForm')">立即修改</el-button>
      <el-button @click="resetForm('bizGoodsOrderForm')">重置</el-button>

    </el-form-item>

  </el-form>
</template>



<script>
import axios from "axios";



export default {
  name: "BizGoodsOrderUpdate",
  data() {
    return {
      bizGoodsOrderForm: {
        id: '',
        code: '',
        customer_name: '',
        goods_name: '',
        count: '',
        price: '',
        sum: '',
        status: '',
        create_time: ''

      },
      //  注意这里的value是int
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
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {

          axios.put('http://localhost:7777/goodsOrder/updateById',this.bizGoodsOrderForm).then(function (resp){

            if(resp.data.result === 'success'){

              _this.$alert('修改成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/bizGoodsOrder')
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
    axios.get('http://localhost:7777/goodsOrder/findById/'+this.$route.query.id).then(function (resp){
      _this.bizGoodsOrderForm = resp.data.result

    })
  }
}
</script>
