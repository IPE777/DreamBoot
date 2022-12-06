<template>
  <el-form :model="bizOrderForm" :rules="rules" ref="bizOrderForm" label-width="100px" class="demo-ruleForm">


    <el-form-item label="编码" prop="code">
      <el-input v-model="bizOrderForm.code"  style="width: 250px"  :disabled="true"></el-input>
    </el-form-item>

    <el-form-item label="客户名称" prop="customer_name">
      <el-input v-model="bizOrderForm.customer_name"  style="width: 250px"  :disabled="true"></el-input>
    </el-form-item>

    <el-form-item label="员工名称" prop="user_name">
      <el-input v-model="bizOrderForm.user_name" style="width: 250px"></el-input>
    </el-form-item>


    <el-form-item label="状态">
      <el-select v-model="bizOrderForm.status" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="创建时间" prop="sum">
      <el-input v-model="bizOrderForm.create_time" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitForm('bizOrderForm')">立即修改</el-button>
      <el-button @click="resetForm('bizOrderForm')">重置</el-button>

    </el-form-item>

  </el-form>
</template>



<script>
import axios from "axios";



export default {
  name: "BizGoodsOrderUpdate",
  data() {
    return {
      bizOrderForm: {
        id: '',
        customer_name: '',
        user_name: '',
        status: '',


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

          axios.put('http://localhost:7777/order/updateById',this.bizOrderForm).then(function (resp){

            if(resp.data.result === 'success'){

              _this.$alert('修改成功', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/bizOrder')
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
    axios.get('http://localhost:7777/order/findById/'+this.$route.query.id).then(function (resp){
      _this.bizOrderForm = resp.data.result

    })
  }
}
</script>
