## 开发

```bash
# 克隆项目
git clone https://gitee.com/y_project/RuoYi-Vue

# 进入项目目录
cd ruoyi-ui

# 安装依赖
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npm.taobao.org

# 启动服务
npm run dev
```

浏览器访问 http://localhost:80

## 发布

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod
```
新增页面导航查看
      url                     详情查看          权限者
equment/Cost/index         设备收费页面        管理员，PI
equment/Cost/CostDetails   设备收费总图        管理员，PI
equment/subScribe/index    设备预约界面           全部

费用视图整体架构图
对象一：行政管理员
1、搜索框  及其功能
   框一：费用类型  ——【全部，设备费用、耗材费用、人工费用】
   框二与框三：时间的选中  
      三种类型及其对应的数据
Day   可选择在一定时间段内的数据   结果也仅仅展示该时间段内的数
Month  可选择某年的具体月份        展示该月的数据  

图一  圆环图与表格的连立  点击表格按钮  即可查看圆环图内的数据对比情况  
问题：是狗有冯家有价值的数据可以挖掘
