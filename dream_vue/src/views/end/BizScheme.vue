<template>
  <div>

    <el-form :inline="true"   class="demo-form-inline">
      <el-form-item>
        <el-button type="primary" @click="dialogFormVisible = true">添加方案</el-button>
      </el-form-item>
      <el-form-item  >
        <el-input v-model="name"  placeholder="客户名称"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="select(name)">查询</el-button>
      </el-form-item>
    </el-form>

    <el-dialog title="增加订单" :visible.sync="dialogFormVisible">
      <el-form :model="bizSchemeForm" :rules="rules" ref="bizSchemeForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="客户名称" prop="customer_name">
          <el-input v-model="bizSchemeForm.customer_name"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="员工名称" prop="user_name">
          <el-input v-model="bizSchemeForm.user_name" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="方案" prop="user_name">
          <el-input type="textarea" v-model="bizSchemeForm.scheme" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="预算" prop="user_name">
          <el-input v-model="bizSchemeForm.budget" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="状态">
          <el-select v-model="bizSchemeForm.status" placeholder="请选择">
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
        <el-button @click="resetForm('bizSchemeForm')">重置</el-button>
        <el-button type="primary" @click="submitForm('bizSchemeForm')">立即创建</el-button>

      </div>
    </el-dialog>

    <el-dialog title="修改用户" :visible.sync="dialogFormVisible2">
      <el-form :model="bizSchemeForm" :rules="rules" ref="bizSchemeForm" label-width="100px" class="demo-ruleForm">

        <el-form-item label="客户名称" prop="customer_name">
          <el-input v-model="bizSchemeForm.customer_name"  style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="员工名称" prop="user_name">
          <el-input v-model="bizSchemeForm.user_name" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="方案" prop="user_name">
          <el-input type="textarea" v-model="bizSchemeForm.scheme" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="预算" prop="user_name">
          <el-input v-model="bizSchemeForm.budget" style="width: 250px"></el-input>
        </el-form-item>

        <el-form-item label="状态">
          <el-select v-model="bizSchemeForm.status" placeholder="请选择">
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
        <el-button @click="resetForm('bizSchemeForm')">重置</el-button>
        <el-button type="primary" @click="editForm('bizSchemeForm')">立即修改</el-button>

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

        prop="customer_name"
        label="客户名称"
        min-width="12%">
      </el-table-column>
      <el-table-column

        prop="user_name"
        label="员工名称"
        min-width="12%">
      </el-table-column>

      <el-table-column

        prop="scheme"
        label="方案"
        min-width="12%">
      </el-table-column>

      <el-table-column

        prop="budget"
        label="预算"
        min-width="12%">
      </el-table-column>

      <el-table-column
        prop="status"
        label="状态"
        min-width="12%">
        <template slot-scope="scope">{{options[scope.row.status]['label']}}</template>
      </el-table-column>

      <el-table-column
        prop="create_time"
        label="创建时间"
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


<script>
import axios from "axios";

export default {
  name: "BizScheme",
  methods: {

    deleteUser(row){
      const _this = this
      this.$confirm('确定删除该数据?','提示',{
        type:'warning'
      }).then(()=>{
        axios.get('http://localhost:7777/scheme/deleteById/'+row.id).then(function (resp){
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
      axios.get('http://localhost:7777/scheme/findById/'+ _this.rowid).then(function (resp){
        _this.bizSchemeForm = resp.data.result

      })
      _this.dialogFormVisible2 = true;

    },
    select(name){
      const _this = this
      if (name !== "") {
        axios.get('http://localhost:7777/scheme/findByName?name=' + name).then(function (resp) {
          console.log(resp)
          _this.tableData = resp.data.result

        })
      }else {
        window.location.reload()
      }
    },
    page(currentPage){
      const _this = this
      axios.get('http://localhost:7777/scheme/getScheme/'+currentPage+'/5').then(function (resp){

        _this.tableData = resp.data.result.records
        _this.total = resp.data.result.total


      })
    },
    submitForm(formName) {
      const _this=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:7777/scheme/add',this.bizSchemeForm).then(function (resp){
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
          axios.put('http://localhost:7777/scheme/updateById',this.bizSchemeForm).then(function (resp){
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
    axios.get('http://localhost:7777/scheme/getScheme/1/5').then(function (resp){
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
      bizSchemeForm: {
        id: '',
        customer_name: '',
        user_name: '',
        scheme: '',
        budget: '',
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
        ]
      }
    }
  }
}
</script>
