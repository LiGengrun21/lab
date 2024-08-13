<!--
    查看本项目的预约费用设计
-->
<template>
  <div class="search">
       <h3>设备费用详情</h3>
        <!--设备的费用名单-->
         <div class="main">
            <el-table
                :data="equimentCostList"
                v-loading="loading"
                @selection-change="handleSelectionChange"
                :header-cell-style="{color:'#000000','text-align': 'center'}"

                style="width: 100%">
                <el-table-column
                label="设备"
                prop="equipmentId"
                align="center"
                :formatter=" typeFormatter"
              >
                </el-table-column >
                <el-table-column align="center"
                label="单位费用"
                sortable
                prop="costNumber">
                </el-table-column>
                <el-table-column align="center"
                label="单位时长(h)"

                prop="minInterval">
                </el-table-column>
                 <el-table-column align="center"
                label="费用类型"
                prop="costType">
                <template slot-scope="scope">
                    <div slot="reference" class="name-wrapper">
                        <el-tag size="medium">{{ scope.row.costType }}</el-tag>
                    </div>
                </template>
                </el-table-column>
                <el-table-column
                prop="artificialCostNumber"
                label="人工费用"
                align="center"
                ></el-table-column>
                 <el-table-column
                label="操作"
                width="120"
                align="center"
                prop="createBy"
                >
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['equipment:show:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                    >删除</el-button>
                    </template>
                </el-table-column>


            </el-table>
        </div>
        <!--类型的增加-->
        <el-form :model="form" :rules="rules" label-width="150" label-position="left"  :inline="false">
            <el-button  type="primary" class="el-icon-circle-plus-outline" plain  @click="openDiglog(0)">添加</el-button>
        </el-form>
        <el-dialog :title="title" :visible.sync="open" width="400px" append-to-body >
            <el-form ref="form" :model="form" :rules="rules" label-width="80px" label-position="left" >

            <el-form-item label="费用类型" prop="costType">
                <el-select v-model="form.costType"  width="300px">
                    <el-option v-for="(item,index) in  CostType" :key="index" :value="item">
                    </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="单位费用" prop="costNumber">
                <el-input v-model="form.costNumber" width="300px"></el-input>
            </el-form-item>
            <el-form-item label="单位时长" prop="minInterval">
                <el-input v-model="form.minInterval" width="300px"><template slot="append">小时</template></el-input>
            </el-form-item>
            <el-form-item label="人工费用" prop="artificialCostNumber">
                <el-input v-model="form.artificialCostNumber" width="300px"><template slot="append">小时</template></el-input>
            </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm()">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
        </el-dialog>
    </div>
</template>
<script>
import { listCost,addCost,updateCost,delCost,getCostType } from '@/api/equipment/cost'
import {listShow} from "@/api/equipment/show"
import {getUserProfile} from "@/api/system/user"
export default {
    props:["equmentId","equimentName"],
    data(){
        return{
            //当前设备的ID
            equimentID:this.equmentId,
            //设备名字
            equimentNa:this.equimentName,
            //匹配的设备的名字
            equipment:1,
            //匹配的收费类型
            type:"",
            //模糊匹配获得的数据
            searchlist:[],
            //设备费用类型
            CostType:["一般成员","优惠人员","院外人员"],
            //数据选择
            queryParams: {
                pageNum: 1,
                pageSize: 6,
            },
            //总数据条数
            total:0,
            //设备收费列表
            equimentCostList:[],
            //设备列表
            equimentList:[],
            //对话框标题
            title:"",
            //是否展示
            open:false,
             // 是否已经选中用户
            multiple: true,
            //是否为单选
            single:false,
            //添加、修改表单
            form:{
                equipmentId:null,
                costNumber:"",
                costType:"",
                MinTime:0.5
            },
            //是否加载
            loading:false,
            rules:{
                costNumber:{
                    requied:true,message:"费用不能为空",trigger: 'change'
                },
                costType:{
                    requied:true,message:"费用类型不能为空",trigger: 'change'
                }
            },
            actionType:0,
        }
    },
    created(){
        this.getList()
        this.getEquimentList()
        //获取用户个人信息
        this.getCostTy()
    },
    watch:{
        equmentId:function(newvalue,oldvalue){
            this.equimentID=newvalue
            this.getList()
        }
    },
    methods:{
        /**获取设备的收费类型 */
        async getCostTy(){
            getCostType({equipmentId:this.equimentID}).then(res=>{
                this.CostType=res.data
            }).catch(err=>{
                console.log(err)

            })
        },
        /**获取设备收费列表 */
        async getList(){
            try{
               const res=await  listCost({equipmentId:this.equimentID})
               this.equimentCostList=[]
                this.equimentCostList=res.rows
                this.total=res.rows.length
                this.loading=false
                console.log( this.equimentCostList)
            }catch(err){
                err
            }
        },
        /**获取当前设备列表 */
        getEquimentList(){
            listShow(this.queryParamss).then(res=>{
                this.equimentList=res.rows
            }).catch(err=>{
            })
        },
        //删除
        handleDelete( row){
             delCost(row.costId).then(res=>{
                 this.getList()
                 this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                    }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                    }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });

             })
             this.loading=false
             })
        },
        /**打开对话框 */
        openDiglog(){
            this.open=true
            this.actionType=0
        },
        /**关闭对话框 */
        cancel(){
            this.open=false,
            this.form={
                equipmentId:null,
                costNumber:"",
                costType:"",
                MinTime:0.5
            }
        },
        /**添加设备收费列表 */
        /**修改设备选项 */
        handleUpdate(row){
            this.open=true
            this.form=row
            this.actionType=1
        },
        //删除选中的选项
        async handledelect(){
        this.$confirm('是否确认删除设备收费的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() =>{
            delCost(this.form.costId).then(res=>{
                 this.getList()
                 this.msgSuccess("删除成功");
                 this.loading=false
             });
        })
             
        },
        /**表单提交 */
        submitForm(){
        this.$refs.form.validate((valid) => {
          if (valid) {
            if(this.actionType===0){
                this.form.equipmentId=this.equimentID
                  addCost(this.form).then(res=>{
                     this.open=false
                     this.getList()
                    this.msgSuccess("添加成功");
              }).catch(err=>{
                  err
              })

            }
            else
            {

                updateCost(this.form).then(res=>{
                    this.open=false
                    this.getList()
                    this.msgSuccess("修改成功");
                    this.open=false
                }).catch(err=>{
                    this.open=false
                })
            }
           this.open=false
          }
         })
        },
        //选中并赋予数据
        handleSelectionChange(selection){
        this.equipmentIds = selection.map(item => item.equipmentId);
        this.single = selection.length != 1;
        this.multiple = !selection.length;
        this.form=selection[0]
        },
         equipmentFilter(item){
                return ((item.CostType===parseInt(this.type))&(item.equipmentId===this.equipment))
        },
        //设备与设备ID对应
        typeFormatter(row){
        for(var item in this.equimentList)
        {
            if(row.equipmentId===this.equimentList[item].equipmentId){
                return this.equimentList[item].equipmentName
            }
        }
        },
        //筛选框
        filterHandler(value, row, column) {
        const string= this.EquipmentFormat(row, column)
        return   string=== value;
      },


    }
}
</script>

<style scoped>
.search .el-form{
    display: flex;
    flex-direction: row;
    margin-top:30px;
    margin-left: 20px;


}
.el-table{
    width: 95vw;
}
.el-form-item{
    width: 300px;
    color: black;
}
.el-select,.el-input{
    width: 200px;
    border:2px initial rgba(30, 101, 207, 0.521);
    border-radius: 5px;
}
.searchbtn{
    margin-left: -80px;
}
.el-form .el-button{
    margin-left: 0px;
    height: 35px;
}
.main{
    width: 95%;
}
.main .pagination{
    float: left;
    margin-left: -50px;
}
</style>
