export namespace Dict {
    /** 新增词典请求参数 */
    export interface AddDictRequestData {
        /** 词典 id */
        bookId: string | undefined
        /** 词典名称 */
        bookName: string | undefined
        /** 词典词数 */
        bookSize: number | undefined
    }

    /** 更新词典请求参数 */
    export interface UpdateDictRequestData {
        /** id */
        id: number | undefined
        /** 词典 id */
        bookId: string | undefined
        /** 词典名称 */
        bookName: string | undefined
        /** 词典词数 */
        bookSize: number | undefined
    }
}
