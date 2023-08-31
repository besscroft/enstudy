package dev.heming.enstudy.common.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * @Description 词库
 * @Author Bess Croft
 * @Date 2023/8/26 13:55
 */
@Data
@EqualsAndHashCode
@Document(collection="book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /** 单词序号 */
    private Integer wordRank;

    /** 单词 */
    private String headWord;

    /** 词典 id */
    private String bookId;

    private Content content;

    @Data
    static class Content {

        private Word word;

    }

    @Data
    static class Word {

        private String wordHead;

        private String wordId;

        private ContentJson content;

    }

    @Data
    static class ContentJson {

        /** 单词相关测试 */
        private List<Exam> exam;

        /** 例句 */
        private Sentence sentence;

        /** 美音音标 */
        private String usphone;

        /** 近义词 */
        private Syno syno;

        /** 英音音标 */
        private String ukphone;

        /** 英音发音https请求参数 */
        private String ukspeech;

        /** 短语 */
        private Phrase phrase;

        /** 同根词 */
        private RelWord relWord;

        /** 美音发音https请求参数 */
        private String usspeech;

        /** 翻译 */
        private List<Trans> trans;

    }

    /**
     * 单词相关测试
     */
    @Data
    static class Exam {

        /** 问题 */
        private String question;

        /** 测试类型 */
        private Integer examType;

        /** 答案 */
        private Answer answer;

        /** 选项 */
        private List<Choices> choices;

        @Data
        static class Answer {

            private String explain;

            private Integer rightIndex;

        }

        @Data
        static class Choices {

            private Integer choiceIndex;

            private String choice;

        }

    }

    /**
     * 例句
     */
    @Data
    static class Sentence {

        /** 描述 */
        private String desc;

        private List<Sentences> sentences;

        @Data
        static class Sentences {

            /** 英语 */
            @JsonProperty("sContent")
            private String sContent;

            /** 中文翻译 */
            @JsonProperty("sCn")
            private String sCn;

        }

    }

    /**
     * 近义词
     */
    @Data
    static class Syno {

        private String desc;

        private List<Synos> synos;

        @Data
        static class Synos {

            /** 词性 */
            private String pos;

            /** 对应词义 */
            private String tran;

            private List<Hwds> hwds;

            @Data
            static class Hwds {

                @JsonProperty("w")
                private String w;

            }

        }

    }

    /**
     * 短语
     */
    @Data
    static class Phrase {

        private String desc;

        private List<Phrases> phrases;

        @Data
        static class Phrases {

            /** 英语 */
            @JsonProperty("pContent")
            private String pContent;

            /** 翻译 */
            @JsonProperty("pCn")
            private String pCn;

        }

    }

    /**
     * 同根词
     */
    @Data
    static class RelWord {

        private String desc;

        private List<Rels> rels;

        @Data
        static class Rels {

            /** 词性 */
            private String pos;

            private List<Words> words;

            @Data
            static class Words {

                /** 英语 */
                private String hwd;

                /** 翻译 */
                private String tran;

            }

        }

    }

    /**
     * 翻译
     */
    @Data
    static class Trans {

        /** 中释 */
        private String tranCn;

        private String descOther;

        /** 词性 */
        private String pos;

        private String descCn;

        /** 英英释义 */
        private String tranOther;

    }

}
