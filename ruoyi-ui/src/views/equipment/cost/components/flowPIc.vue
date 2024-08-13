<template>
    <div class="flow">
        <el-steps :active="3">
            <el-step title="步骤 1" icon="el-icon-edit">描述一</el-step>
            <el-step title="步骤 2" icon="el-icon-upload">描述二</el-step>
            <el-step title="步骤 3" icon="el-icon-picture">描述三</el-step>
            <el-step title="步骤 4" icon="el-icon-picture">描述四</el-step>
        </el-steps>
    </div>
</template>
<script>
export default {
    data() {
        return {
            
        }
    },
}
</script>
