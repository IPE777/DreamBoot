import Vue from 'vue'
import Router from 'vue-router'
import Index from '../views/end/Index'
import User from '../views/end/User'
import Customer from '../views/end/Customer'
import BizGoods from '../views/end/BizGoods'
import BizGoodsOrder from "../views/end/BizGoodsOrder";
import BizOrder from "../views/end/BizOrder";
import BizScheme from "../views/end/BizScheme";
import Team from "../views/end/Team"
import Content from "../views/end/Content";
import Comment from "../views/end/Comment";
import Login from "../views/end/Login"
import FrontLogin from "../views/front/FrontLogin"
import FrontRegister from "../views/front/FrontRegister"
import FrontIndex from "../views/front/FrontIndex"
import FrontPerson from "../views/front/FrontPerson"
import FrontMessage from "../views/front/FrontMessage"
import FrontShop from "../views/front/FrontShop"
import FrontLike from "../views/front/FrontLike"
import FrontPublish from "../views/front/FrontPublish"
import FrontBusiness from "../views/front/FrontBusiness"
import FrontGoods from "../views/else/FrontGoods"
import Message from "../views/end/Message"
import Sensitive from "../views/end/Sensitive"


Vue.use(Router)

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/end/login',
      component: Login,
      show: false
    },
    {
      path: '/end/index',
      name: "人员管理",
      component: Index,
      show: true,

      redirect: "/end/user",
      children:[
        {
          path: '/end/user',
          name: "员工管理",
          component: User
        },
        {
          path: '/end/customer',
          name: "用户管理",
          component: Customer
        }
      ]
    },
    {
      path: '/end/biz',
      name: '商机管理',
      show: true,
      component: Index,

      children:[
        {
          path: '/end/bizGoods',
          name: "商品管理",
          component: BizGoods
        },
        {
          path: '/end/bizGoodsOrder',
          name: "商品订单",
          component: BizGoodsOrder
        },
        {
          path: '/end/bizOrder',
          name: "装修订单",
          component: BizOrder
        },
        {
          path: '/end/sensitive',
          name: "敏感词汇",
          component: Sensitive
        }
        // {
        //   path: '/end/bizScheme',
        //   name: "装修方案",
        //   component: BizScheme
        // }
      ]
    },
    {
      path: '/front',
      name: '前台管理',
      show: true,
      component: Index,

      children:[
        // {
        //   path: '/end/team',
        //   name: "团队管理",
        //   component: Team
        // },
        {
          path: '/end/content',
          name: "内容管理",
          component: Content
        },
        {
          path: '/end/comment',
          name: "评论管理",
          component: Comment
        },
        {
          path: '/end/message',
          name: "消息管理",
          component: Message
        }
      ]
    },
    {
      path: '/front/login',
      component: FrontLogin
    },
    {
      path: '/register',
      component: FrontRegister
    },
    {
      path: '/front/index',
      component: FrontIndex,
      meta: {
        requireAuth: true
      },
    },
    {
      path: '/front/person',
      component: FrontPerson
    },
    {
      path: '/front/message',
      component: FrontMessage
    },
    {
      path: '/front/shop',
      component: FrontShop
    },
    {
      path: '/front/like',
      component: FrontLike
    },
    {
      path: '/front/publish',
      component: FrontPublish
    },
    {
      path: '/front/business',
      component: FrontBusiness
    },
    {
      path: '/front/goods',
      component: FrontGoods
    }


  ]
})
