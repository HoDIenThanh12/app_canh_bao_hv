package com.example.tiki.app_canhbao.constants;

import com.example.tiki.app_canhbao.backend.SoTayGVBE;
import com.example.tiki.app_canhbao.entity.QuyCheVCHT;
import com.example.tiki.app_canhbao.entity.SoTayCVHT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstantSoTayCVHT {
    static SoTayGVBE tayGVBE;
    static List<SoTayCVHT> sotayGVBEList;
    static String [] QCDTTHTTT1;
    static String [] NDQCDTTHTTT1;
    static String [] QCCTSV2;
    static String [] NDQCCTSV2;
    static String [] QCCVHT3;
    static String [] NDQCCVHT3;
    static String [] QDPCCVHT4;
    static String [] NDQDPCCVHT4;
    public static List<SoTayCVHT> inItChuong1(){
        QCDTTHTTT1 = new String[]
                {"1. Phạm vi điều chỉnh và đối tượng áp dụng",
                        "2. Phân cấp tổ chức và quản lý đào tạo",
                        "3. Nguyên tắc đảm bảo chất lượng và hiệu quả giáo dục",
                        "4. Hình thức dạy học, tín chỉ học tập"};
        NDQCDTTHTTT1=new String[]{
                "nd1: Quy chế này quy định về đào tạo bậc đại học tại Trường Đại học Thủ Dầu Một, bao\n" +
                        "gồm: chương trình đào tạo; tổ chức và quản lý đào tạo; kiểm tra và thi học phần; xét và\n" +
                        "công nhận tốt nghiệp.\n" +
                        "Quy chế này áp dụng đối với các đơn vị và cá nhân tham gia vào quá trình đào tạo\n" +
                        "bậc đại học (chính quy, thường xuyên) tại Trường Đại học Thủ Dầu Một. ",
                "nd2: 1. Cấp trường\n" +
                        "Chỉ đạo và điều hành thống nhất công tác tổ chức và quản lý đào tạo theo cơ chế mở,\n" +
                        "liên thông, liên kết và hợp tác giữa các đơn vị đào tạo.\n" +
                        "Điều phối sử dụng các nguồn lực chung (nhân lực, cơ sở vật chất - kỹ thuật) phục vụ\n" +
                        "đào tạo.\n" +
                        "Quản lý toàn diện công tác tổ chức và quản lý đào tạo của các Khoa, Trung tâm,\n" +
                        "Chương trình đào tạo.\n" +
                        "2. Đơn vị đào tạo\n" +
                        "Khoa, Trung tâm, Chương trình đào tạo tổ chức và quản lý đào tạo các ngành học,\n" +
                        "học phần đã được Trường giao nhiệm vụ; công nhận kết quả học tập của sinh viên; xây\n" +
                        "dựng học liệu dùng chung.\n" +
                        "Xây dựng những chương trình đào tạo mới, chú trọng tính liên thông, liên ngành\n" +
                        "trình Hiệu trưởng xem xét quyết định ban hành và giao nhiệm vụ tổ chức đào tạo.",
                "nd3: 1. Nội dung đào tạo, phương pháp dạy - học, kiểm tra đánh giá và phương thức quản\n" +
                        "lý phải phù hợp và hướng tới chuẩn đầu ra của chương trình đào tạo.\n" +
                        "2. Phát triển các chương trình đào tạo mới, có tính liên thông, liên ngành, xuyên\n" +
                        "ngành, đáp ứng nhu cầu của xã hội.\n" +
                        "3. Gắn đào tạo với nghiên cứu khoa học, tăng cường hoạt động thực hành, thực tập,\n" +
                        "thực tế.\n" +
                        "4. Nhà trường đầu tư các điều kiện đảm bảo chất lượng giáo dục.\n" +
                        "5. Đảm bảo chương trình đáp ứng chuẩn kiểm định chất lượng giáo dục. ",
                "nd4: 1. Hình thức dạy học\n" +
                        "Quá trình đào tạo được thực hiện theo quy trình sau: (1) dạy học lý thuyết; (2) dạy\n" +
                        "học trên mô hình, mô phỏng; (3) bài tập thực tế; (4) bài tập thực nghiệm. Trong đó:\n" +
                        "1.1. Giờ lý thuyết: sinh viên học tập trên lớp qua bài giảng trực tiếp của giảng viên tại\n" +
                        "lớp học (có sự hỗ trợ của hệ thống elearning).\n" +
                        "1.2. Giờ thực hành: sinh viên học tập qua thực hành, thực tập, làm thí nghiệm, làm\n" +
                        "bài tập, thảo luận; qua mô hình, mô phỏng; qua bài tập tình huống trên các thiết bị điển\n" +
                        "hình; thực hành thực tập trên hoạt động thực tiễn.\n" +
                        "1.3. Giờ tự học: sinh viên tự học tập và nghiên cứu theo kế hoạch và nội dung do\n" +
                        "giảng viên giao, có sự tương tác giữa sinh viên với giảng viên thông qua hệ thống hỗ trợ\n" +
                        "quản lý nội dung học tập (LMS - Learning Management System) và được kiểm tra đánh giá\n" +
                        "theo kế hoạch trong đề cương chi tiết.\n" +
                        "2. Tín chỉ\n" +
                        "Tín chỉ được sử dụng để tính khối lượng học tập của sinh viên. Một tín chỉ được quy\n" +
                        "định bằng 15 giờ học lý thuyết; 30 - 45 giờ thực hành, thí nghiệm hoặc thảo luận; 45 - 90\n" +
                        "Phòng ĐTĐH – Ban hành năm 2019\n" +
                        "6 | P a g e\n" +
                        "giờ thực tập tại cơ sở; 45 - 60 giờ làm tiểu luận, bài tập lớn hoặc đồ án, khóa luận tốt\n" +
                        "nghiệp.\n" +
                        "Đối với những học phần lý thuyết hoặc thực hành, thí nghiệm, để tiếp thu được một\n" +
                        "tín chỉ sinh viên phải dành ít nhất 30 giờ chuẩn bị của cá nhân.\n" +
                        "Một giờ học được tính bằng 50 phút.\n",
        };
        //Tạo danh sách
        List<String > lQCDTTHTTT1= Arrays.asList(QCDTTHTTT1);
        List<String > lNDQCDTTHTTT1= Arrays.asList(QCDTTHTTT1);
        //init list sổ tay CVHT
        sotayGVBEList= new ArrayList<>();
        for (int i=0;i<lQCDTTHTTT1.size();i++){
//         Log.e("data: ","l"+'\n'+lQDPCCVHT4.get(i)+'\n'+
//                                 "\t"+lNDQDPCCVHT4.get(i));
            SoTayCVHT sotay = new SoTayCVHT(i+1,lQCDTTHTTT1.get(i),lNDQCDTTHTTT1.get(i));
            sotayGVBEList.add(sotay);
        }
        return sotayGVBEList;
    }
    public static List<SoTayCVHT> inItChuong2(){
        QCCTSV2 = new String[]
                {"1. Phạm vi điều chỉnh và đối tượng áp dụng",
                        "2. Mục đích của công tác sinh viên",
                        "3. Yêu cầu của công tác sinh viên",
                        "4. Nhiệm vụ của sinh viên"
                };
        NDQCCTSV2=new String[]{
                "nd1: 1. Quy chế này quy định nhiệm vụ và quyền của sinh viên; khen thưởng và kỷ\n" +
                        "luật sinh viên; nội dung công tác sinh viên; hệ thống tổ chức, quản lý công tác sinh viên và tổ\n" +
                        "chức thực hiện.\n" +
                        "2. Quy chế này áp dụng đối với sinh viên (SV) chương trình đào tạo đại học hệ\n" +
                        "chính quy của Trường Đại học Thủ Dầu Một (ĐH TDM).",
                "nd2: Công tác sinh viên là một trong những công tác tác trọng tâm của Trường, nhằm bảo đảm\n" +
                        "thực hiện mục tiêu giáo dục và đào tạo SV của Trường ĐH TDM phát triển toàn diện, có đạo\n" +
                        "đức, tri thức, sức khỏe, thẩm mỹ và nghề nghiệp, trung thành với lý tưởng độc lập dân tộc và\n" +
                        "chủ nghĩa xã hội; hình thành và bồi dưỡng nhân cách, phẩm chất và năng lực công dân, đáp ứng\n" +
                        "yêu cầu của sự nghiệp xây dựng và bảo vệ Tổ quốc, bảo đảm SV tốt nghiệp đạt các tiêu chuẩn đã\n" +
                        "nêu trong tầm nhìn, sứ mạng và mục tiêu của Trường.\n",
                "nd3: 1. SV được bảo đảm điều kiện thực hiện đầy đủ quyền và nhiệm vụ trong quá trình học tập và\n" +
                        "rèn luyện tại Trường.\n" +
                        "2. Công tác sinh viên phải thực hiện đúng chủ trương, đường lối của Đảng, chính\n" +
                        "sách, pháp luật của Nhà nước, các quy định của Bộ Giáo dục và Đào tạo và của Trường ĐH\n" +
                        "TDM\n" +
                        "3. Công tác sinh viên phải bảo đảm dân chủ, khách quan, công bằng, công khai,\n" +
                        "minh bạch trong các vấn đề có liên quan đến sinh viên.\n" +
                        "4. Công tác sinh viên góp phần giúp SV đạt chuẩn đầu ra của Trường.",
                "nd4: 1. Chấp hành chủ trương, đường lối của Đảng, chính sách, pháp luật của Nhà\n" +
                        "nước, Điều lệ trường đại học và các quy chế, nội quy của Trường.\n" +
                        "2. Học tập, rèn luyện theo chương trình, kế hoạch giáo dục, đào tạo của cơ sở\n" +
                        "giáo dục đại học; chủ động, tích cực tự học, nghiên cứu, sáng tạo và rèn luyện đạo đức, lối sống.\n" +
                        "3. Tôn trọng nhà giáo, cán bộ quản lý, viên chức và nhân viên của cơ sở giáo\n" +
                        "dục đại học; đoàn kết, giúp đỡ lẫn nhau trong quá trình học tập và rèn luyện; thực hiện tốt nếp\n" +
                        "sống văn hóa trong trường học.\n" +
                        "4. Giữ gìn và bảo vệ tài sản; hành động góp phần bảo vệ, xây dựng và phát huy\n" +
                        "truyền thống của Trường.\n" +
                        "5. Thực hiện đầy đủ quy định về việc khám sức khỏe đầu khóa và khám sức khỏe định kỳ\n" +
                        "trong thời gian học tập theo quy định của Trường.\n" +
                        "6. Đóng học phí, bảo hiểm y tế đầy đủ, đúng thời hạn.\n" +
                        "7. Tham gia lao động công ích, hoạt động tình nguyện, hoạt động xã hội vì cộng đồng phù\n" +
                        "hợp với năng lực và sức khỏe theo yêu cầu của Trường.\n" +
                        "8. Chấp hành nghĩa vụ làm việc có thời hạn theo sự Điều động của Nhà nước khi được\n" +
                        "hưởng học bổng, chi phí đào tạo do Nhà nước cấp hoặc do nước ngoài tài trợ theo Hiệp định ký\n" +
                        "kết với Nhà nước, nếu không chấp hành phải bồi hoàn học bổng, chi phí đào tạo theo quy định\n" +
                        "của Chính phủ.\n" +
                        "Phòng ĐTĐH – Ban hành năm 2019\n" +
                        "17 | P a g e\n" +
                        "9. Tham gia phòng, chống tiêu cực, gian lận trong học tập, thi cử và các hoạt động khác\n" +
                        "của sinh viên; kịp thời báo cáo với khoa, phòng chức năng, Hiệu trưởng hoặc các cơ quan có\n" +
                        "thẩm quyền khi phát hiện những hành vi tiêu cực, gian lận trong học tập, thi cử hoặc những hành\n" +
                        "vi vi phạm pháp luật, vi phạm nội quy, quy chế khác của sinh viên, cán bộ, nhà giáo trong\n" +
                        "Trường.\n" +
                        "10. Tham gia công tác bảo đảm an ninh, trật tự, an toàn giao thông, phòng chống tội phạm,\n" +
                        "tệ nạn xã hội trong trường học, gia đình và cộng đồng.\n" +
                        "11. Tham gia các hoạt động khảo sát lấy ý kiến phản hồi của SV về hoạt động giảng dạy,\n" +
                        "chương trình học.\n" +
                        "12. Kịp thời thông báo cho Trường nếu có sự thay đổi về thông tin cá nhân. Thực hiện đầy\n" +
                        "đủ trách trách nhiệm của SV cư trú tại địa phương và nếu có thay đổi địa chỉ ngoại trú phải báo\n" +
                        "cáo cho Trường theo quy định.\n" +
                        "13. Tham gia đầy đủ tuần sinh hoạt công dân sinh viên đầu khóa, đầu năm, cuối khóa và các\n" +
                        "đợt đột xuất khi Trường yêu cầu",
        };
        //tạo list
        List<String > lQCCTSV2= Arrays.asList(QCCTSV2);
        List<String > lNDQCCTSV2= Arrays.asList(NDQCCTSV2);
        //init list sổ tay CVHT
        sotayGVBEList= new ArrayList<>();
        for (int i=0;i<lQCCTSV2.size();i++){
//         Log.e("data: ","l"+'\n'+lQDPCCVHT4.get(i)+'\n'+
//                                 "\t"+lNDQDPCCVHT4.get(i));
            SoTayCVHT sotay = new SoTayCVHT(i+1,lQCCTSV2.get(i),lNDQCCTSV2.get(i));
            sotayGVBEList.add(sotay);
        }
        return sotayGVBEList;
    }
    public static List<SoTayCVHT> inItChuong3(){
        QCCVHT3 = new String[]
                {"1. Phạm vi và đối tượng áp dụng",
                        "2. Mục đích",
                        "3. Trách nhiệm của CVHT ",
                        "4. Tiêu chuẩn của CVHT"};
        NDQCCVHT3=new String[]{
                "nd1: Văn bản này quy định chế độ làm việc đối với giảng viên được phân công làm công tác\n" +
                        "cố vấn học tập (CVHT) cho sinh viên đại học chính quy và thường xuyên được đào tạo theo hệ\n" +
                        "thống tín chỉ bao gồm: cơ cấu tổ chức, chức năng, nhiệm vụ, quyền hạn, thời gian và nội dung\n" +
                        "làm việc với lớp; chế độ báo cáo, quyền lợi, khen thưởng và kỷ luật của CVHT.\n" +
                        "Phòng ĐTĐH – Ban hành năm 2019\n" +
                        "25 | P a g e\n" +
                        "Đối tượng: CVHT lớp học (theo quyết định của Nhà trường) và CVHT lớp học phần (tất\n" +
                        "cả giảng viên đều tham gia).",
                "nd2: 1. Thực hiện nhiệm vụ của CVHT theo quy định của Nhà trường.\n" +
                        "2. Nhằm giúp Trưởng khoa, Giám đốc Chương trình và các đơn vị có liên quan trong công\n" +
                        "tác quản lí học vụ. Theo dõi tình hình của lớp sinh viên, kịp thời đề xuất với Nhà trường về\n" +
                        "phương pháp xử lí đối với những tình huống phát sinh trong quá trình đào tạo.\n" +
                        "3. Nhằm giúp cho sinh viên tìm hiểu thông tin, liên hệ công việc liên quan đến học tập và\n" +
                        "rèn luyện.",
                "nd3: 1. Nắm vững chương trình đào tạo, hướng dẫn sinh viên xây dựng kế hoạch học tập, lựa\n" +
                        "chọn đăng ký các học phần phù hợp với điều kiện học tập của sinh viên và mục tiêu, yêu cầu của\n" +
                        "chương trình đào tạo.\n" +
                        "2. Hướng dẫn phương pháp học tập, nghiên cứu, phát triển kỹ năng nghề nghiệp, kỹ năng\n" +
                        "bổ trợ cho sinh viên; thường xuyên theo dõi kết quả học tập của sinh viên;\n" +
                        "3. Giúp đỡ sinh viên giải quyết những khó khăn vướng mắc trong học tập; nhắc nhở sinh\n" +
                        "viên khi thấy kết quả học tập của sinh viên giảm sút.\n" +
                        "4. Phối hợp với Khoa, Chương trình đào tạo, phòng Đào tạo đại học, phòng Công tác sinh\n" +
                        "viên và các đơn vị liên quan để tạo điều kiện cho sinh viên học tập, đánh giá điểm rèn luyện của\n" +
                        "sinh viên.\n" +
                        "5. Làm cầu nối giúp sinh viên kết nối với các doanh nghiệp, cơ quan sử dụng lao động để\n" +
                        "sinh viên thực hành thực tập và tìm kiếm việc làm.\n",
                "nd4: 1. Là các giảng viên, cán bộ thuộc Khoa, Chương trình có chuyên ngành đúng hoặc ngành\n" +
                        "gần với ngành làm CVHT; am hiểu nội dung, chương trình đào tạo, phương thức đào tạo theo\n" +
                        "quy chế đào tạo học chế tín chỉ.\n" +
                        "2. Có tinh thần trách nhiệm trong công tác, nhiệt tình đối với nhiệm vụ được giao, phải\n" +
                        "tuân thủ đúng nguyên tắc trong thực thi nhiệm vụ của CVHT.\n" +
                        "3. Nắm vững các quy chế liên quan đến công tác đào tạo, quy chế HSSV, các quy định của\n" +
                        "Nhà trường, Chương trình đào tạo, các chuẩn đầu ra… để tư vấn cho sinh viên.\n" +
                        "Điều 5. Nguyên tắc làm việc của CVHT\n" +
                        "1. CVHT phải luôn quan tâm đến lợi ích của sinh viên, không làm điều gì gây thiệt hại cho\n" +
                        "sinh viên; Giữ gìn những điều riêng tư của sinh viên.\n" +
                        "2. Không bình phẩm, đánh giá một cá nhân hay tổ chức nào đó trước mặt sinh viên làm\n" +
                        "giảm uy tín của cá nhân hay tổ chức đó.\n" +
                        "3. Có hành động ứng xử phù hợp với đạo đức nghề nghiệp, có tư cách đạo đức tốt để làm\n" +
                        "gương cho sinh viên.",
        };
        //tạo list
        List<String > lQCCVHT3= Arrays.asList(QCCVHT3);
        List<String > lNDQCCVHT3= Arrays.asList(NDQCCVHT3);
        //init list sổ tay CVHT
        sotayGVBEList= new ArrayList<>();
        for (int i=0;i<lQCCVHT3.size();i++){
//         Log.e("data: ","l"+'\n'+lQDPCCVHT4.get(i)+'\n'+
//                                 "\t"+lNDQDPCCVHT4.get(i));
            SoTayCVHT sotay = new SoTayCVHT(i+1,lQCCVHT3.get(i),lNDQCCVHT3.get(i));
            sotayGVBEList.add(sotay);
        }
        return sotayGVBEList;
    }
    public static List<SoTayCVHT> inItChuong4(){
        QDPCCVHT4 = new String[]
                {"1. Phạm vi và đối tượng áp dụng",
                        "2. Mục đích",
                        "3. Trách nhiệm của CVHT ",
                        "4. Tiêu chuẩn của CVHT"};
        NDQDPCCVHT4=new String[]{
                "nd1: Văn bản này quy định chế độ làm việc đối với giảng viên được phân công làm công tác\n" +
                        "cố vấn học tập (CVHT) cho sinh viên đại học chính quy và thường xuyên được đào tạo theo hệ\n" +
                        "thống tín chỉ bao gồm: cơ cấu tổ chức, chức năng, nhiệm vụ, quyền hạn, thời gian và nội dung\n" +
                        "làm việc với lớp; chế độ báo cáo, quyền lợi, khen thưởng và kỷ luật của CVHT.\n" +
                        "Phòng ĐTĐH – Ban hành năm 2019\n" +
                        "25 | P a g e\n" +
                        "Đối tượng: CVHT lớp học (theo quyết định của Nhà trường) và CVHT lớp học phần (tất\n" +
                        "cả giảng viên đều tham gia).",
                "nd2: 1. Thực hiện nhiệm vụ của CVHT theo quy định của Nhà trường.\n" +
                        "2. Nhằm giúp Trưởng khoa, Giám đốc Chương trình và các đơn vị có liên quan trong công\n" +
                        "tác quản lí học vụ. Theo dõi tình hình của lớp sinh viên, kịp thời đề xuất với Nhà trường về\n" +
                        "phương pháp xử lí đối với những tình huống phát sinh trong quá trình đào tạo.\n" +
                        "3. Nhằm giúp cho sinh viên tìm hiểu thông tin, liên hệ công việc liên quan đến học tập và\n" +
                        "rèn luyện.",
                "nd3: 1. Nắm vững chương trình đào tạo, hướng dẫn sinh viên xây dựng kế hoạch học tập, lựa\n" +
                        "chọn đăng ký các học phần phù hợp với điều kiện học tập của sinh viên và mục tiêu, yêu cầu của\n" +
                        "chương trình đào tạo.\n" +
                        "2. Hướng dẫn phương pháp học tập, nghiên cứu, phát triển kỹ năng nghề nghiệp, kỹ năng\n" +
                        "bổ trợ cho sinh viên; thường xuyên theo dõi kết quả học tập của sinh viên;\n" +
                        "3. Giúp đỡ sinh viên giải quyết những khó khăn vướng mắc trong học tập; nhắc nhở sinh\n" +
                        "viên khi thấy kết quả học tập của sinh viên giảm sút.\n" +
                        "4. Phối hợp với Khoa, Chương trình đào tạo, phòng Đào tạo đại học, phòng Công tác sinh\n" +
                        "viên và các đơn vị liên quan để tạo điều kiện cho sinh viên học tập, đánh giá điểm rèn luyện của\n" +
                        "sinh viên.\n" +
                        "5. Làm cầu nối giúp sinh viên kết nối với các doanh nghiệp, cơ quan sử dụng lao động để\n" +
                        "sinh viên thực hành thực tập và tìm kiếm việc làm.\n",
                "nd4: 1. Là các giảng viên, cán bộ thuộc Khoa, Chương trình có chuyên ngành đúng hoặc ngành\n" +
                        "gần với ngành làm CVHT; am hiểu nội dung, chương trình đào tạo, phương thức đào tạo theo\n" +
                        "quy chế đào tạo học chế tín chỉ.\n" +
                        "2. Có tinh thần trách nhiệm trong công tác, nhiệt tình đối với nhiệm vụ được giao, phải\n" +
                        "tuân thủ đúng nguyên tắc trong thực thi nhiệm vụ của CVHT.\n" +
                        "3. Nắm vững các quy chế liên quan đến công tác đào tạo, quy chế HSSV, các quy định của\n" +
                        "Nhà trường, Chương trình đào tạo, các chuẩn đầu ra… để tư vấn cho sinh viên.\n" +
                        "Điều 5. Nguyên tắc làm việc của CVHT\n" +
                        "1. CVHT phải luôn quan tâm đến lợi ích của sinh viên, không làm điều gì gây thiệt hại cho\n" +
                        "sinh viên; Giữ gìn những điều riêng tư của sinh viên.\n" +
                        "2. Không bình phẩm, đánh giá một cá nhân hay tổ chức nào đó trước mặt sinh viên làm\n" +
                        "giảm uy tín của cá nhân hay tổ chức đó.\n" +
                        "3. Có hành động ứng xử phù hợp với đạo đức nghề nghiệp, có tư cách đạo đức tốt để làm\n" +
                        "gương cho sinh viên.",
        };
        //tạo list
        List<String > lQDPCCVHT4= Arrays.asList(QDPCCVHT4);
        List<String > lNDQDPCCVHT4= Arrays.asList(NDQDPCCVHT4);
        sotayGVBEList= new ArrayList<>();
        for (int i=0;i<lQDPCCVHT4.size();i++){
//         Log.e("data: ","l"+'\n'+lQDPCCVHT4.get(i)+'\n'+
//                                 "\t"+lNDQDPCCVHT4.get(i));
            SoTayCVHT sotay = new SoTayCVHT(i+1,lQDPCCVHT4.get(i),lNDQDPCCVHT4.get(i));
            sotayGVBEList.add(sotay);
        }
        return sotayGVBEList;
    }
}
