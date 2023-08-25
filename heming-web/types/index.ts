// 请求响应参数
export interface Result<T = any> {
  code: number
  message: string
  data: T
}

// 分页请求响应参数
export interface PageResultData<T = any> extends Result {
  list: T[]
  pageNum: number
  pageSize: number
  total: number
  totalPage: number
}

// 分页请求参数
export interface ReqPage {
  pageNum: number
  pageSize: number
}

// 登录模块
export namespace Login {
  export interface ReqLoginForm {
    username: string
    password: string
  }
  export interface ResLogin {
    token: string
    expiresIn: number
    tokenHead: string
    refreshToken: string
  }
}
