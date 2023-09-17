export namespace Word {
    /** 新增单词请求参数 */
    export interface AddWordRequestData {
        /** 单词数据 */
        wordJson: any | undefined
        /** 单词序号 */
        wordRank: number | undefined
        /** 单词 */
        headWord: string | undefined
        /** 词典 id */
        bookId: string | undefined
    }

    /** 更新单词请求参数 */
    export interface UpdateWordRequestData {
        /** id */
        id: number | undefined
        /** 单词数据 */
        wordJson: any | undefined
        /** 单词序号 */
        wordRank: number | undefined
        /** 单词 */
        headWord: string | undefined
        /** 词典 id */
        bookId: string | undefined
    }
}
