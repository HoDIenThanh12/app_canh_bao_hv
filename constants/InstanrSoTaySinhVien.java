package com.example.tiki.app_canhbao.constants;

import com.example.tiki.app_canhbao.backend.SoTayGVBE;
import com.example.tiki.app_canhbao.entity.QuyCheVCHT;
import com.example.tiki.app_canhbao.entity.SotaySV;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstanrSoTaySinhVien {
    static List<SotaySV> lSoTaySinhVien;
    static String [] ST_1;
    static String [] ND_ST_1;
    static String [] ST_2;
    static String [] ND_ST_2;
    static String [] ST_3;
    static String [] ND_ST_3;
    static String [] ST_4;
    static String [] ND_ST_4;
    static String [] ST_5;
    static String [] ND_ST_5;
    public static List<SotaySV> inItSotayChuong1(){
        ST_1 = new String[]
                {"1. Tên trường"};
        ND_ST_1=new String[]{
                "nd1: Tên tiếng Việt: TRƯỜNG ĐẠI HỌC THỦ DẦU MỘT\n" +
                 "Tên tiếng Anh: THU DAU MOT UNIVERSITY\n" +
                 "Tên viết tắt: TDMU\n\n"+
                 "Thủ Dầu Một trước khi trở thành tên trường đại học công lập ở " +
                 "tỉnh Bình Dương đã là địa danh của vùng đất rộng lớn. Theo các " +
                 "nhà nghiên cứu, Thủ là đứng đầu, Dầu là tên một loài gỗ lớn; Một là số 1, thứ nhất. " +
                 "Thủ Dầu Một đã trở thành địa danh hành chính cấp hạt (1869), cấp tỉnh (1899) bao gồm " +
                 "vùng đất rộng lớn của miền Đông Nam Bộ. Tên gọi này không chỉ ghi dấu ấn lịch sử hàng " +
                 "trăm năm mà còn thể hiện khát vọng của người xưa về vùng đất này. Tên gọi Thủ Dầu Một " +
                  "đã trở thành biểu tượng văn hóa và phát triển, một giá trị lịch sử, văn hóa to lớn của " +
                  "tỉnh Bình Dương, của miền Đông Nam Bộ.\n"+
                   "Trong quá trình thực hiện đề án thành lập trường đại học công lập của tỉnh Bình Dương, " +
                        "khi hầu hết các trường đại học địa phương đều lấy tên tỉnh, thành để làm tên trường, " +
                        "trong khi đó Bình Dương đã có Trường Đại học dân lập Bình Dương, " +
                        "Thầy Nguyễn Văn Hiệp (nay là Chủ tịch Hội đồng trường) đã lựa chọn tên Thủ Dầu Một " +
                        "đặt cho ngôi trường mới. Tên gọi Trường Đại học Thủ Dầu Một đã gợi lên mối quan hệ " +
                        "chặt chẽ với lịch sử, văn hóa, địa điểm, tư tưởng, truyền thống và khát vọng của cư " +
                        "dân nơi đây. Ngoài việc thực hiện chức năng định danh của một cơ sở đào tạo đại học, " +
                        "tên Trường Đại học Thủ Dầu Một còn biểu đạt tình cảm, ý chí, khát vọng vươn lên tiếp nối " +
                        "và phát triển những giá trị văn hóa to lớn mà các thế hệ đi trước đã dày công vun đắp."
        };
        //Tạo danh sách
        List<String > lQCDTTHTTT1= Arrays.asList(ST_1);
        List<String > lNDQCDTTHTTT1= Arrays.asList(ND_ST_1);
        //init list sổ tay CVHT
        lSoTaySinhVien= new ArrayList<>();
        for (int i=0;i<lQCDTTHTTT1.size();i++){
            SotaySV sotay = new SotaySV(i+1,lQCDTTHTTT1.get(i),lNDQCDTTHTTT1.get(i));
            lSoTaySinhVien.add(sotay);
        }
        return lSoTaySinhVien;
    }
    public static List<SotaySV> inItSoTayChuong2(){
        ST_2 = new String[]
                {"1. Sứ mệnh",
                        "2. Mục tiêu",
                        "3. Tầm nhìn",
                };
        ND_ST_2=new String[]{
                "nd1: 1. Là trung tâm văn hóa, giáo dục và khoa học, công nghệ. Cung cấp nguồn nhân lực, sản phẩm khoa học và công nghệ có chất lượng phục vụ phát triển kinh tế, xã hội và hội nhập quốc tế tỉnh Bình Dương, miền Đông Nam Bộ và cả nước.\n",
                "nd2: Phát triển theo định hướng ứng dụng, trở thành trường đại học đa ngành, đa lĩnh vực theo các chuẩn mực quốc tế tiên tiến, hiện đại\n",
                "nd3: 1. Trở thành đại học thông minh theo mô hình chuỗi kết hợp: khuôn viên thông minh, công nghệ thông minh, quản trị thông minh, nghiên cứu thông minh, con người thông minh, ảnh hưởng thông minh.\n"
        };
        //tạo list
        List<String > lQCCTSV2= Arrays.asList(ST_2);
        List<String > lNDQCCTSV2= Arrays.asList(ND_ST_2);
        //init list sổ tay CVHT
        lSoTaySinhVien= new ArrayList<>();
        for (int i=0;i<lQCCTSV2.size();i++){
            SotaySV sotay = new SotaySV(i+1,lQCCTSV2.get(i),lNDQCCTSV2.get(i));
            lSoTaySinhVien.add(sotay);
        }
        return lSoTaySinhVien;
    }
    public static List<SotaySV> inItSoTayChuong3(){
        ST_3 = new String[]
                {"1. Khát vọng",
                        "2. Trách nhiệm",
                        "3. Sáng tạo",
                };
        ND_ST_3=new String[]{
                "nd1: Có ý thức vươn lên đỉnh cao tri thức, ước vọng vươn tới những\n" +
                        "điều tốt đẹp và quyết tâm đeo bám, thực hiện một cách mạng mẽ nhất.\n",
                "nd2: . Có thái độ tích cực và tinh thần trách nhiệm với chính mình, với\n" +
                        "gia đình, xã hội, tổ quốc và nhân loại, có đủ năng lực và kỹ năng để chịu trách nhiệm về công việc và hành vi của mình.\n",
                "nd3: Có tư duy sáng tạo, nắm vững vấn đề, hình thành ý tưởng, phân\n" +
                        "tích, đánh giá ý tưởng, chỉ đạo thực hiện giải pháp để tạo ra những giá trị mới đáp ứng yêu cầu và phục vụ cho sự phát triển của xã hội.\n",
        };
        //tạo list
        List<String > lQCCVHT3= Arrays.asList(ST_3);
        List<String > lNDQCCVHT3= Arrays.asList(ND_ST_3);
        //init list sổ tay CVHT
        lSoTaySinhVien= new ArrayList<>();
        for (int i=0;i<lQCCVHT3.size();i++){
            SotaySV sotay = new SotaySV(i+1,lQCCVHT3.get(i),lNDQCCVHT3.get(i));
            lSoTaySinhVien.add(sotay);
        }
        return lSoTaySinhVien;
    }
    public static List<SotaySV> inItChuong4(){
        ST_4 = new String[]
                {"1. Nghiên Cứu – Trải Nghiệm – Phục Vụ Cộng Đồng",};
        ND_ST_4=new String[]{
                "Xác định mục tiêu học tập và mục tiêu công dân thông qua quá trình nghiên cứu, học tập kết hợp với phục vụ cộng đồng; phát triển các nội dung phục vụ cộng đồng lồng ghép vào chương trình đào tạo, tạo ra văn hóa phục vụ cho sinh viên thông qua quá trình trải nghiệm học tập gắn với việc phục vụ cộng đồng một cách có kế hoạch, có định hướng, có cơ hội để phản hồi, suy ngẫm từ những trải nghiệm học tập – phục vụ cộng đồng.\n" +
                        "\n" +
                        "Tạo cơ hội để sinh viên thể hiện kinh nghiệm và kết nối các mục tiêu học tập, nghiên cứu học thuật với mục tiêu công dân được lồng ghép trong hoạt động học tập, trải nghiệm; giúp sinh viên thấu hiểu sâu sắc về bối cảnh kinh tế, xã hội, văn hóa để hình thành những khái niệm nêu ra trong môn học.\n" +
                        "\n" +
                        "Đem lại cho sinh viên các nhóm kỹ năng quan trọng: kỹ năng phản biện, kỹ năng giao tiếp, kỹ năng nghề nghiệp, xã hội; trách nhiệm dân sự; trách nhiệm công dân toàn cầu.\n" +
                        "\n" +
                        "Hướng tới việc hình thành một thế hệ nhân lực tiên tiến, nhân văn, nhận thức sâu sắc và có trách nhiệm với xã hội; thấu hiểu các vấn đề mang tính toàn cầu; phát triển năng lực làm việc chuyên nghiệp thông qua việc kết nối chặt chẽ với doanh nghiệp và cộng đồng.\n",
        };
        //tạo list
        List<String > lQDPCCVHT4= Arrays.asList(ST_4);
        List<String > lNDQDPCCVHT4= Arrays.asList(ND_ST_4);
        lSoTaySinhVien= new ArrayList<>();
        for (int i=0;i<lQDPCCVHT4.size();i++){
            SotaySV sotay = new SotaySV(i+1,lQDPCCVHT4.get(i),lNDQDPCCVHT4.get(i));
            lSoTaySinhVien.add(sotay);
        }
        return lSoTaySinhVien;
    }
    public static List<SotaySV> inItSoTayChuong5(){
        ST_5 = new String[]
                {"KHÁI QUÁT LỊCH SỬ PHÁT TRIỂN",};
        ND_ST_5=new String[]{
                "Trường Đại học Thủ Dầu Một là cơ sở đào tạo đại học công lập của tỉnh Bình Dương, được thành lập năm 2009 trên cơ sở nâng cấp từ Trường Cao đẳng Sư phạm Bình Dương. Ngay từ buổi đầu xây dựng, Trường Đại học Thủ Dầu Một đã xác định phương hướng chiến lược và quan điểm xây dựng trường đại học phù hợp, gắn bó chặt chẽ với thực tiễn phát triển kinh tế, xã hội của tỉnh Bình Dương, các tỉnh miền Đông Nam Bộ - Vùng kinh tế trọng điểm phía Nam, phù hợp với xu thế hội nhập quốc tế. Chỉ sau một thời gian ngắn, Trường đã định hình là một cơ sở đại học đa ngành, đa lĩnh vực, phát triển mạnh cả về quy mô, chất lượng và hiệu quả đào tạo, đáp ứng yêu cầu công nghiệp hóa của địa phương và cả nước, đáp ứng nhu cầu học tập của nhân dân; bước đầu khẳng định uy tín và thương hiệu của một trường  đại học mới trên đất Bình Dương.\n" +
                        "\n" +
                        "Được thành lập trong bối cảnh giáo dục đại học Việt Nam bước vào thời kỳ đổi mới toàn diện để hội nhập quốc tế, Trường Đại học Thủ Dầu Một đã mạnh dạn thoát khỏi lối đi cũ mòn, tìm ra những khâu đột phá để làm nên sức sống và tương lai của một trường đại học. Nhà trường sớm đặt trọng tâm vào việc xây dựng đội ngũ, trọng dụng và thu hút nhân tài đảm bảo cho sự phát triển ổn định, đồng thời chú trọng nghiên cứu mở rộng các ngành nghề mới có ích cho xã hội, cần thiết cho sự phát triển kinh tế - xã hội của tỉnh Bình Dương  và các tỉnh miền Đông Nam Bộ. Hiện nay, trường đang đào tạo 38 chương trình đại học, 10 chương trình sau đại học với quy mô trên 16.000 sinh viên, 1.000 học viên sau đại học, đội ngũ cán bộ trên 700 người gồm 20 giáo sư, phó giáo sư, 150 tiến sĩ, trên 500 thạc sĩ; trên 85% sinh viên ra trường có việc làm ngay, nhiều sinh viên có việc làm từ năm thứ 3. Hoạt động khoa học công nghệ được chú trọng theo hướng liên kết với doanh nghiệp và ứng dụng thực tiễn. Một số sản phẩm khoa học của trường đã\n" +
                        "\n" +
                        "\n" +
                        "được chuyển giao cho các doanh nghiệp, nông dân và người tiêu dùng mang lại hiệu quả kinh tế, xã hội thiết thực. Trong công tác quản trị đại học, Nhà trường đã tạo sự bứt phá để xây dựng mô hình quản trị tiên tiến: trường đa ngành - khoa đa ngành - viện nghiên cứu. Trường Đại học Thủ Dầu Một là một trong những cơ sở đào tạo đại học đầu tiên ở Việt Nam tiên phong nghiên cứu và chuyển đổi cấp bộ môn thành cấp chương trình đào tạo, đổi mới sâu rộng phương thức quản lý đào tạo truyền thống sang phương thức quản lý hiện đại. Trường cũng là cơ sở đào tạo đại học sớm áp dụng mô hình Hội đồng trường theo hướng thực quyền và trách nhiệm.\n" +
                        "\n" +
                        "Hiện nay, trước tác động của cuộc cách mạng công nghiệp 4.0, Nhà trường đang nỗ lực tiếp cận những thành tựu mới của thế giới để nâng cao chất lượng đào tạo. Trong hoạt động đào tạo, Trường chủ trương chuyển trọng tâm từ cách học tiếp nhận kiến thức sang cách học trang bị kỹ năng làm việc và sáng tạo. Chương trình đào tạo của trường được cấu trúc hoàn toàn theo hướng đào tạo hai giai đoạn, giai đoạn 1 trang bị kiến thức chung cho tất cả các ngành, kiến thức liên ngành và lĩnh vực, giai đoạn 2 đào tạo kiến thức chuyên ngành tỷ lệ 50% lý thuyết, 50% thực hành với 4 nhóm kiến thức: lý thuyết, mô hình mô phỏng, bài tập điển hình, thực hành thực tập. Để đáp ứng yêu cầu của xã hội, các chương trình đào tạo thống nhất xác định kiến thức và kỹ năng là chuẩn chung bắt buộc nhưng sẽ được áp dụng linh hoạt cho nhiều đối tượng học khác nhau, với mỗi đối tượng sẽ có phương pháp, cách thức phù hợp để đảm bảo đáp ứng chuẩn đặt ra. Về cơ sở vật chất, Trường đang chuyển hướng từ đầu tư vào hạ tầng vật chất sang đầu tư chủ yếu vào hạ tầng kỹ thuật để phục vụ tất cả các chương trình học hỗn hợp; sinh viên có thể học mọi lúc, mọi nơi (học trong lớp và trực tuyến, khai thác tài nguyên mở, tương tác với giảng viên), toàn bộ các không gian của trường đã và đang đầu tư thành không gian học tập và sáng tạo. Trong năm 2020, Trường đã đầu tư trung tâm dữ liệu lớn đồng thời dành một phần kinh phí thích đáng để bước đầu xây dựng khuôn viên học tập thông minh, lớp học kiến tạo, xây dựng các nền tảng học tập trực tuyến để phục vụ đào tạo sinh viên… Trong hoạt\n" +
                        "\n" +
                        "\n" +
                        "động khoa học công nghệ, Trường đang thực hiện các chương trình hợp tác với các chuyên gia trong và ngoài nước thúc đẩy các hướng nghiên cứu liên ngành, xuyên ngành, cập nhật những tiến bộ mới nhất của khoa học công nghệ trong nghiên cứu và đào tạo.\n" +
                        "\n" +
                        "Với hành trang của hơn 10 năm xây dựng và trưởng thành, với sự thấu hiểu và sẵn sàng giải quyết khó khăn thách thức, sự dấn thân của đội ngũ lãnh đạo và toàn thể cán bộ, giảng viên, học viên, sinh viên, Trường Đại học Thủ Dầu Một chắc chắn sẽ phát triển bền vững, trở thành địa chỉ tin cậy trong đào tạo, nghiên cứu, chuyển giao khoa học công nghệ của Việt Nam và ASEAN. Mục tiêu đứng vào top 350 các trường đại học tốt nhất châu Á vào năm 2025 của Thầy và trò Trường Đại học Thủ Dầu Một chắn chắn sẽ trở thành hiện thực.\n",
        };
        //tạo list
        List<String > lQDPCCVHT5= Arrays.asList(ST_5);
        List<String > lNDQDPCCVHT5= Arrays.asList(ND_ST_5);
        lSoTaySinhVien= new ArrayList<>();
        for (int i=0;i<lQDPCCVHT5.size();i++){
            SotaySV sotay = new SotaySV(i+1,lQDPCCVHT5.get(i),lNDQDPCCVHT5.get(i));
            lSoTaySinhVien.add(sotay);
        }
        return lSoTaySinhVien;
    }
}
