package com.example.tiki.app_canhbao.constants;

import com.example.tiki.app_canhbao.entity.QuyCheVCHT;
import com.example.tiki.app_canhbao.entity.SoTayCVHT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstantQuyCheCVHT {
    static List<QuyCheVCHT> ListQuyChe;
    static String [] QC_1;
    static String [] ND_QC_1;
    static String [] QC_2;
    static String [] ND_QC_2;
    static String [] QC_3;
    static String [] ND_QC_3;
    static String [] QC_4;
    static String [] ND_QC_4;
    public static List<QuyCheVCHT> inItQuyChe1(){
        QC_1 =new String[]
                {"Điều 1. Phạm vi và đối tượng áp dụng.",
                 "Điều 2. Mục đích.",
                 "Điều 3. Trách nhiệm của CVHT"};
        ND_QC_1=new String[]
                {   "nd điều 1: Văn bản này quy định chế độ làm việc đối với giảng viên được phân công làm\n" +
                        "công tác cố vấn học tập (CVHT) cho sinh viên đại học chính quy và thường xuyên\n" +
                        "được đào tạo theo hệ thống tín chỉ bao gồm: cơ cấu tổ chức, chức năng, nhiệm vụ,\n" +
                        "quyền hạn, thời gian và nội dung làm việc với lớp; chế độ báo cáo, quyền lợi, khen\n" +
                        "thưởng và kỷ luật của CVHT.\n"+
                        "Đối tượng: CVHT lớp học (theo quyết định của Nhà trường) và CVHT lớp học\n" +
                        "phần (tất cả giảng viên đều tham gia).",

        /*nd điều 2: */ "1. Thực hiện nhiệm vụ của CVHT theo quy định của Nhà trường.\n" +
                        "2. Nhằm giúp Trưởng khoa, Giám đốc Chương trình và các đơn vị có liên quan\n" +
                        "trong công tác quản lí học vụ. Theo dõi tình hình của lớp sinh viên, kịp thời đề\n" +
                        "xuất với Nhà trường về phương pháp xử lí đối với những tình huống phát sinh\n" +
                        "-trong quá trình đào tạo.\n" +
                        "3. Nhằm giúp cho sinh viên tìm hiểu thông tin, liên hệ công việc liên quan đến học\n" +
                        "tập và rèn luyện.\n",
        /*nd điều 3: */ "1. Nắm vững chương trình đào tạo, hướng dẫn sinh viên xây dựng kế hoạch học\n" +
                        "tập, lựa chọn đăng ký các học phần phù hợp với điều kiện học tập của sinh viên\n" +
                        "và mục tiêu, yêu cầu của chương trình đào tạo.\n"+
                        "2. Hướng dẫn phương pháp học tập, nghiên cứu, phát triển kỹ năng nghề nghiệp,\n" +
                        "kỹ năng bổ trợ cho sinh viên; thường xuyên theo dõi kết quả học tập của sinh\n" +
                        "viên;\n" +
                        ". Giúp đỡ sinh viên giải quyết những khó khăn vướng mắc trong học tập; nhắc\n" +
                        "nhở sinh viên khi thấy kết quả học tập của sinh viên giảm sút.\n" +
                        "4. Phối hợp với Khoa, Chương trình đào tạo, phòng Đào tạo đại học, phòng Công\n" +
                        "tác sinh viên và các đơn vị liên quan để tạo điều kiện cho sinh viên học tập, đánh\n" +
                        "giá điểm rèn luyện của sinh viên.\n" +
                        "5. Làm cầu nối giúp sinh viên kết nối với các doanh nghiệp, cơ quan sử dụng lao\n" +
                        "động để sinh viên thực hành thực tập và tìm kiếm việc làm.",
                        };
        //Tạo danh sách
        List<String > lQCDTTHTTT1= Arrays.asList(QC_1);
        List<String > lNDQCDTTHTTT1= Arrays.asList(ND_QC_1);
        //init list sổ tay QCCVHT
        ListQuyChe= new ArrayList<>();
        for (int i=0;i<lQCDTTHTTT1.size();i++){
            QuyCheVCHT qc = new QuyCheVCHT(i+1,lQCDTTHTTT1.get(i),lNDQCDTTHTTT1.get(i));
            ListQuyChe.add(qc);
        }
        return ListQuyChe;
    }
    public static List<QuyCheVCHT> inItQuyChe2(){
        QC_2 =new String[]
                { "Điều 4. Tiêu chuẩn của CVHT.",
                  "Điều 5. Nguyên tắc làm việc của CVHT.",
                  "Điều 6. Quy trình phân công CVHT."
                };
        ND_QC_2=new String[]
                {
                        //Điều 4
                    "1. Là các giảng viên, cán bộ thuộc Khoa, Chương trình có chuyên ngành đúng\n"+
                    "hoặc ngành gần với ngành làm CVHT; am hiểu nội dung, chương trình đào tạo,\n" +
                    "phương thức đào tạo theo quy chế đào tạo học chế tín chỉ.\n"+
                    "2. Có tinh thần trách nhiệm trong công tác, nhiệt tình đối với nhiệm vụ được giao,\n"+
                    "phải tuân thủ đúng nguyên tắc trong thực thi nhiệm vụ của CVHT.\n" +
                    "3. Nắm vững các quy chế liên quan đến công tác đào tạo, quy chế HSSV, các quy\n"+
                    "định của Nhà trường, Chương trình đào tạo, các chuẩn đầu ra... để tư vấn cho\n" +
                    "sinh viên.",
                        //Điều 5
                    "1. CVHT phải luôn quan tâm đến lợi ích của sinh viên, không làm điều gì gây thiệt\n" +
                    "hại cho sinh viên; Giữ gìn những điều riêng tư của sinh viên.\n" +
                    "2. Không bình phẩm, đánh giá một cá nhân hay tổ chức nào đó trước mặt sinh viên\n" +
                    "làm giảm uy tín của cá nhân hay tô chức đó. \n" +
                    "3. Có hành động ứng xử phù hợp với đạo đức nghề nghiệp, có tư cách đạo đức tốt\n" +
                    "để làm gương cho sinh viên.",
                        //điều 6
                    "I. Việc phân công CVHT được thực hiện theo các bước sau :"+
                    "a. Trước khi năm học mới bắt đầu 15 ngày, Giám đốc chương trình phân công\n" +
                    "giảng viên làm CVHT, đảm bảo mỗi lớp có 01 CVHT và mỗi CVHT không\n" +
                    "phụ trách quá 02 lớp; Khoa có trách nhiệm điều phối tất cả các Chương trình\n" +
                            "đào tạo thuộc khoa, sau khi phân công 01 CVHT phụ trách 2 lớp mà vẫn\n" +
                            "chưa đủ thì báo cáo để Nhà trường tiếp tục điều phối; Khoa gửi danh sách\n" +
                            "CVHT về Phòng Đảo tạo Đại học.\n" +
                            "b. Tất cả giảng viên đều làm nhiệm vụ CVHT; Nhiệm vụ cụ thê của từng\n" +
                            "CVHT do Giám đốc chương trình phân công. Trong đó đảm bảo đội ngũ\n" +
                            "CVHT ở cả lớp khóa học và lớp học phần để tư vấn cho sinh viên.\n" +
                            "\n" +
                            "c. Phòng Đào tạo Đại học tập hợp danh sách, tham mưu cho Hiệu trưởng ra\n" +
                            "quyết định đội ngũ CVHT.\n" +
                            "\n" +
                            "d. Khoa, Chương trình công bố quyết định phân công CVHT của Hiệu trưởng\n" +
                            "đến giảng viên.\n" +
                            "\n" +
                            "CVHT không thực hiện hoặc thực hiện không đúng nhiệm vụ, bị nhắc nhở lần\n" +
                            "thứ 2 hoặc bị kỷ luật từ hình thức cảnh cáo trở lên thì Trưởng khoa, Giám đốc\n" +
                            "chương trình đề nghị thay thế.\n" +
                            "\n" +
                            "Trong trường hợp CVHT bị thay thế, Trưởng khoa, Giám đốc chương trình lập\n" +
                            "danh sách đề nghị Hiệu trưởng phân công CVHT mới và đề nghị hình thức kỷ\n" +
                            "luật đối với Giảng viên là CVHT không hoàn thành nhiệm vụ."

                };
        //Tạo danh sách
        List<String > lQCDTTHTTT1= Arrays.asList(QC_2);
        List<String > lNDQCDTTHTTT1= Arrays.asList(ND_QC_2);
        //init list  QCCVHT
        ListQuyChe= new ArrayList<>();
        for (int i=0;i<lQCDTTHTTT1.size();i++){
            QuyCheVCHT qc = new QuyCheVCHT(i+1,lQCDTTHTTT1.get(i),lNDQCDTTHTTT1.get(i));
            ListQuyChe.add(qc);
        }
        return ListQuyChe;
    }
    public static List<QuyCheVCHT> inItQuyChe3(){
        QC_3=new String[]
                {" Điều 7. Chức năng của CVHT",
                "Điều 8. Nhiệm vụ của CVHT",
                "Điều 9. Quyền hạn của CVHT"};
        ND_QC_3=new String[]
                {
                        //quy chế 7
                        "1. Tư vấn, hỗ trợ thông tin và định hướng quá trình học tập, rèn luyện, thực hiện\n" +
                        "'quy chê đào tạo, quyên và nghĩa vụ của sinh viên.\n" +
                        "2. Theo dõi quá trình học tập, rèn luyện và tư vấn cho sinh viên về kế hoạch học\n" +
                        "tập, đăng ký môn học; giúp sinh viên điều chỉnh hoặc lựa chọn đúng ngành\n" +
                        "trong quá trình học tập... ì\n" +
                        "3. Tham mưu cho lãnh đạo Khoa, Chương trình các vấn đề liên quan đến công tác\n" +
                        "giáo dục và đào tạo, nghiên cứu khoa học của sinh viên và đào tạo theo nhu câu\n" +
                        "xã hội.",
                  //quy chế 8
                "1. Tư vấn cho sinh viên đăng ký học tập\n"+
                "a. Tư vấn cho sinh viên của lớp phụ trách xây dựng kế hoạch học tập cho toàn\n" +
                        "khóa học; thời hạn đăng ký học, mức học phí, đăng ký học phần (học phần bắt\n" +
                        "buộc, học phần tự chọn, học phần tiên quyết) trong từng học kỳ một cách hợp lý\n" +
                        "để đảm bảo hoàn thành chương trình đào tạo của toàn khoá học. Theo dõi tình\n" +
                        "hình, kết quả học tập của sinh viên theo từng học kỳ để tư vấn cho sinh viên\n" +
                        "đăng ký, điều chỉnh kế hoạch học tập cho phù hợp.\n" +
                        "\n" +
                        "b. Hướng dẫn sinh viên về quy trình đăng ký học tập (mã số học phân, tên học\n" +
                        "phần, đăng ký lớp học phần, số lượng tín chỉ.... ). Tư vấn cho sinh viên đề việc\n" +
                        "tăng, giảm học phần phù hợp với quy định của Trường, phù hợp với khả năng,\n" +
                        "điều kiện học tập của sinh viên, kiểm tra và xác nhận tư vấn vào số đăng ký học\n" +
                        "tập cho sinh viên.\n\n"+
                "2. Quản lý, tư vấn và hỗ trợ sinh viên trong các hoạt động học tập, rèn luyện tham\n" +
                        "gia các hoạt động Đoàn, Hội.\n"+
                "a. Nắm vững nội dung chương trình đào tạo, các quy chế của Bộ Giáo dục và\n" +
                        "Đào tạo về đào tạo, về công tác HSSV, các quy định của nhà trường về quá trình\n" +
                        "học tập, rèn luyện của sinh viên (chuẩn đầu ra tin học, ngoại ngữ, công tác xã\n" +
                        "hội, GDTC —- GDQPAN; các hoạt động phong trào, công tác tình nguyện của\n" +
                        "Đoàn, Hội)... để tư vấn, hỗ trợ sinh viên trong quá trình học tập, rèn luyện tại\n" +
                        "Trường.\n" +
                        "\n" +
                        "b. Tiếp nhận thông tin của Nhà trường, của các đơn vị trực thuộc cung cấp để\n" +
                        "thực hiện tốt những phương pháp quản lý sinh viên.\n" +
                        "\n" +
                        "c. Tô chức bâu chọn hoặc kiện toàn ban cán sự lớp chuyên ngành vào đâu mỗi\n" +
                        "năm học, báo cáo lãnh đạo đơn vị công nhận danh sách phân công nhiệm vụ.\n" +
                        "\n" +
                        "d. Tổ chức đánh giá kết quả rèn luyện sinh viên: Phô biến hướng dẫn thực hiện\n" +
                        "quy chế, chủ trì họp lớp đánh giá điểm rèn luyện cho từng sinh viên. Hoàn thành\n" +
                        "bảng tổng hợp đánh giá kết quả rèn luyện đúng thời gian quy định.\n" +
                        "\n" +
                        "Ẻ Khuyến khích, tạo điều kiện và tự vấn cho sinh viên thảm gia các hoạt động\n" +
                        "học thuật, nghiên cứu khoa học, rèn luyện kỹ năng thu thập, xử lý thông tin, tiêp\n" +
                        "cận doanh nghiệp và các hoạt động văn, thê, mỹ lành mạnh, bồ ích.\n" +
                        "\n" +
                        "£. Tổ chức họp lớp theo thời khóa biểu của Nhà trường, tìm hiểu và giải quyết\n" +
                        "những vấn đề nảy sinh đối với sinh viên, nhắc nhở những sinh viên có ý thực\n" +
                        "học tập chưa tốt.\n"+
                "g. Thông báo cho sinh viên về thời gian và địa điểm làm việc để sinh viên gặp\n" +
                        "xin ý kiến tư vấn về các vấn đề trong học tập, rèn luyện (khuyến khích có hình\n" +
                        "thức làm việc qua các kênh internet để tiết kiệm thời gian, thông tin trao đôi\n" +
                        "được lưu giữ, nhiều sinh viên có thể cùng tham khảo).\n" +
                        "\n" +
                        "h. Ghi chép đầy đủ nội dung họp lớp và thông tin sinh viên của lớp phụ trách\n" +
                        "vào Số công tác cố vấn học tập.\n" +
                        "\n" +
                        "¡. Thực hiện chế độ báo cáo online 1 lần/ tháng (cho CTĐT, Khoa, Phòng Đào\n" +
                        "tạo Đại học) và bằng văn bản, nộp cho Chương trình và các đơn vị trực thuộc có\n" +
                        "liên quan sau khi tổ chức họp cuối học kỳ (1 lần/ 1 học kỳ).",
                //quy chế điều 9
                "Được tham gia cuộc họp của Hội đồng CVHT, các cuộc họp của Hội đồng Khoa\n" +
                        "có liên quan đến công tác sinh viên thuộc lớp phụ trách.\n" +
                        "\n" +
                        "Được phép truy cập một số tài liệu có liên quan tới chế độ, chính sách, chương\n" +
                        "trình đào tạo, giáo trình, học liệu... để phục vụ công tác tư vấn, hướng dẫn sinh\n" +
                        "viên trong học tập.\n" +
                        "\n" +
                        "Tham gia thảo luận, đề xuất ý kiến tại các cuộc họp Hội đồng các cấp có liên\n" +
                        "quan đến sinh viên của lớp phụ trách.\n" +
                        "\n" +
                        "Tham gia Hội đồng khen thưởng kỷ luật cấp Chương trình, Khoa và cấp Trường\n" +
                        "trong trường hợp cần thiết đối với lớp sinh viên phụ trách. Có quyền khiếu nại\n" +
                        "kết quả đánh giá xếp loại CVHT nếu thấy kết luận của Hội đồng chưa thoả đáng.\n" +
                        "Được tính chế độ theo qui định chung của Trường."
                };
        //Tạo danh sách
        List<String > lQCDTTHTTT1= Arrays.asList(QC_3);
        List<String > lNDQCDTTHTTT1= Arrays.asList(ND_QC_3);
        //init list  QCCVHT
        ListQuyChe= new ArrayList<>();
        for (int i=0;i<lQCDTTHTTT1.size();i++){
            QuyCheVCHT qc = new QuyCheVCHT(i+1,lQCDTTHTTT1.get(i),lNDQCDTTHTTT1.get(i));
            ListQuyChe.add(qc);
        }
        return ListQuyChe;
    }
    public static List<QuyCheVCHT> inItQuyChe4(){
        QC_4=new String[]
                {"Điêu 10. Thời gian và nội dung làm việc với lớp :",
                "Điều 11. Chế độ báo cáo của công tác CVHT"};
        ND_QC_4=new String[]
                {
                //quy chế điều 10
                "1. Giữ mối liên hệ thường xuyên với lớp sinh viên để nắm tình hình lớp, mỗi tuần\n" +
                        "CVHT bố trí ít nhất 1 giờ để sinh viên được gặp hoặc tư vấn trao đôi, và có số\n" +
                        "lưu các hoạt động gặp gỡ sinh viên.\n" +
                        "\n" +
                        "2. Hàng tháng làm việc với Ban cán sự lớp, tổ chức họp lớp định kỳ theo quy định.\n" +
                        "Các buổi họp lớp cần ghi Biên bản vào Số công tác cố vấn học tập và nộp Báo\n" +
                        "cáo tình hình lớp sinh viên cho lãnh đạo Chương trình hàng tháng, cụ thể:\n"+
                        "a. Họp đâu học kỳ và hàng tháng với các nội dụng:\n" +
                        "\n" +
                        "Côhg bố thành phần ban cán sự lớp do CVHT chỉ định (đối với các lớp\n" +
                        "năm thứ nhất) hoặc tô chức họp lớp bầu chọn ban cán sự lớp. Phân công nhiệm\n" +
                        "vụ cụ thể cho ban cán sự lớp. Thông báo cho sinh viên được biết về vai trò, trách\n" +
                        "nhiệm của CVHT đối với lớp sinh viên. Quy định thời gian, địa điểm sinh viên\n" +
                        "có thể gặp xin tư vấn hoặc trao đôi qua điện thoại, thư điện tử...v.v.\n" +
                        "\n" +
                        "Phô biên công tác năm học và các học kỳ.\n" +
                        "\n" +
                        "Phố biến, hướng dẫn những nội dung cơ bản của các quy chế, quy định về\n" +
                        "học tập và rèn luyện của sinh viên, chú ý ý những bồ sung, thay đôi mới trong các\n" +
                        "quy định hiện hành. Hướng dẫn sinh viên tìm hiểu các thông tin trong “Số tay\n" +
                        "sinh viên” của Nhà trường.\n" +
                        "\n" +
                        "Hướng dẫn những điều cần lưu ý về phương pháp học tập, nghiên cứu khoa\n" +
                        "học, tham gia các hoạt động đoàn thê, xã hội.\n" +
                        "\n" +
                        "Năm các thông tin về sinh viên và cung câp thông tin CVHT cho sinh viên.\n" +
                        "b. Họp cuôi học kỳ với các nội dung:\n" +
                        "\n" +
                        "Sơ kết các nội dung đã thực hiện trong học kỳ. Tổ chức đánh giá kết quả\n" +
                        "rèn luyện sinh viên và đánh giá CVHTT theo kê hoạch của Nhà trường.\n" +
                        "\n" +
                        "Phối hợp tổ chức đánh giá kết quả rèn luyện sinh viên theo kế hoạch của\n" +
                        "Nhà trường.\n" +
                        "Thông báo những bồ sung, thay đổi trong quy định hiện hành (nếu có).\n" +
                        "\n" +
                        "Thông qua kết quả học tập trong học kỳ đề nhắc nhở, định hướng lập kế\n" +
                        "hoạch học tập và đăng ký học phần trong học kỳ tiếp theo cho sinh viên.\n" +
                        "\n" +
                        "Thực hiện các công việc khác theo yêu cầu quản lý của Trường, của Khoa,\n" +
                        "của Chương trình đào tạo có liên quan đến lớp phụ trách (nếu có).\n" +
                        "\n" +
                        "3. CVHT có thể làm việc đột xuất với Ban cán sự lớp, hoặc tổ chức họp lớp để giải\n" +
                        "quyết các công việc theo yêu cầu của Trường, Khoa, Chương trình và lớp.\n" +
                        "\n" +
                        "4. Đối với những sinh viên có hoàn cảnh đặc biệt, gặp khó khăn trong học tập và\n" +
                        "cuộc sống, CVHT cần quan tâm, giúp đỡ, động viên các em vượt qua khó khăn\n" +
                        "để hoàn thành kế hoạch học tập đã đặt ra.\n",
                //quy chế điều 11
                "1. Hàng tháng, CVHT có trách nhiệm báo cáo với lãnh đạo Chương trình đào tạo\n" +
                        "tình hình sinh viên của lớp phụ trách (theo mục ¡ điều §).\n" +
                        "\n" +
                        "2. Báo cáo vào cuối mỗi học kỳ (kể cả học kỳ phụ).\n" +
                        "\n" +
                        "3. Báo cáo kịp thời các hiện tượng phát sinh trong sinh viên; các sinh viên gặp\n" +
                        "khó khăn cần được giúp đỡ. Đề nghị khen thưởng, kỷ luật sinh viên lớp phụ trách ."
                };
        //Tạo danh sách
        List<String > lQCDTTHTTT1= Arrays.asList(QC_4);
        List<String > lNDQCDTTHTTT1= Arrays.asList(ND_QC_4);
        //init list sổ tay QCCVHT
        ListQuyChe= new ArrayList<>();
        for (int i=0;i<lQCDTTHTTT1.size();i++){
            QuyCheVCHT qc = new QuyCheVCHT(i+1,lQCDTTHTTT1.get(i),lNDQCDTTHTTT1.get(i));
            ListQuyChe.add(qc);
        }
        return ListQuyChe;
    };

}
