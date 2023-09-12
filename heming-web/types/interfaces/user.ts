export namespace User {
  /** 用户启用状态更改请求参数 */
  export interface ChangeUserStatusRequestData {
    userId: number | undefined
    status: number | undefined
  }

  /** 新增用户请求参数 */
  export interface AddUserRequestData {
    /** 用户名 */
    username: string | undefined
    /** 密码 */
    password: string | undefined
    /** 头像 */
    avatar: string | undefined
    /** 角色 */
    role: string | undefined
    /** 邮箱 */
    email: string | undefined
    /** 昵称 */
    name: string | undefined
    /** 手机号 */
    telephone: number | undefined
    /** 备注 */
    remark: string | undefined
  }

  /** 更新用户请求参数 */
  export interface UpdateUserRequestData {
    /** 用户id */
    id: number | undefined
    /** 头像 */
    avatar: string | undefined
    /** 角色 */
    role: string | undefined
    /** 邮箱 */
    email: string | undefined
    /** 昵称 */
    name: string | undefined
    /** 手机号 */
    telephone: number | undefined
    /** 备注 */
    remark: string | undefined
  }

  /** 密码修改请求参数 */
  export interface UpdatePasswordData {
    /** 用户 id */
    userId: number | undefined
    /** 是否是自己 */
    isSelf: boolean
    /** 旧密码 */
    oldPassword: string | undefined
    /** 新密码 */
    newPassword: string | undefined
  }
}
