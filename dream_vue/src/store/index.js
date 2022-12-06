import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    user: {

    },
    user2: {

    }
  },
  mutations: {
    login (state, user) {
      state.user = user
      window.localStorage.setItem('user',user)

      console.log( window.localStorage)
    },
    logout (state) {
      state.user = []
      window.localStorage.removeItem('user')

      console.log( window.localStorage)
    },
    login2 (state, user) {
      state.user2 = user
      window.localStorage.setItem('user2',user)

      console.log( window.localStorage)
    },
    logout2 (state) {

      window.localStorage.removeItem('user2')

      console.log( window.localStorage)
    },
  }
})
