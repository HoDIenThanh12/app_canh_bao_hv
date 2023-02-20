package com.example.tiki.app_canhbao.constants;

import com.example.tiki.app_canhbao.entity.VanBanCVHT;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InstantCacVanBanCVHT {
    private static List<VanBanCVHT> lVanBan;
    static String [] VB_1;
    static String [] ND_VB_1;
    static String [] VB_2;
    static String [] ND_VB_2;
    static String [] VB_3;
    static String [] ND_VB_3;
    public static List<VanBanCVHT> getVB1(){
        VB_1=new String[]{
                "QUYẾT ĐỊNH\n" +
                        " Ban hành Quy cheế Đào tạo đại học theo chế tín chỉ\n",
                "QUY TRÌNH CÔNG TÁC CỐ VẤN HỌC TẬP",
                "BÁO CÁO CÔNG TÁC CỐ VẤN HỌC TẬP THÁNG....",
        };
        ND_VB_1=new String[]{
                "HIỆU TRƯỞNG TRƯỜNG ĐẠI HỌC THỦ DẦU MỘT\n"+
                "Căn cứ Quyết định số 70/2014/QĐ-TTg ngày 10/12/2014 của Thủ tướng\n" +
                        "Chính phủ về việc ban hành Điều lệ Trường Đại học;\n" +
                        "\n" +
                        "Căn cứ Quyết định số 72/2009/QĐ-UBND ngày 23/10/2009 của Ủy ban nhân\n" +
                        "dân Tỉnh Bình Dương về việc ban hành Quy chế tổ chức và hoạt động của Trường\n" +
                        "Đại học Thủ Dầu Một;\n" +
                        "\n" +
                        "Theo đề nghị của Trưởng phòng Đào tạo đại học\n" +
                        "\n" +
                        "QUYÉT ĐỊNH:\n" +
                        "\n" +
                        "Điều 1. Ban hành Quy chế Cố vấn học tập (lớp học và lớp học phần) hệ chính\n" +
                        "quy và thường xuyên theo học chế tín chỉ.\n" +
                        "\n" +
                        "Điều 2. Quyết định này có hiệu lực thi hành kể từ ngày ký.\n" +
                        "\n" +
                        "Điều 3. Trưởng các Phòng, Khoa, Trung tâm, Viện, Giám đốc Chương trình\n" +
                        "và các đơn vị liên quan chịu trách nhiệm thi hành Quyết định này./.",
                "1. Mục đích\n" +
                        "Hướng dẫn cố vấn học tập thực hiện nhiệm vụ theo quy định của Nhà trường.\n" +
                        "2. Phạm vi áp dụng\n" +
                        "Giảng viên được phân công làm công tác cố vấn học tập cho sinh viên đại học chính quy\n" +
                        "và thường xuyên.\n" +
                        "3. Căn cứ pháp lý\n" +
                        "Căn cứ Quyết định số 1472/QĐ-ĐHTDM ban hành ngày 27/09/2019 về Quy chế\n" +
                        "công tác Cố vấn học tập tại trường Đại học Thủ Dầu Một của Hiệu trưởng Trường Đại học\n" +
                        "Thủ Dầu Một.\n"+
                        "Hướng dẫn chi tiết\n" +
                        "Bước 1: Khoa đề xuất danh sách phân công CVHT gửi về Phòng ĐTĐH - BM.18A\n" +
                        "Bước 2: CVHT tiếp nhận danh sách sinh viên, bầu chọn ban cán sự lớp và cập nhật quy chế,\n" +
                        "quy trình đào tạo.\n" +
                        "Bước 3: CVHT lập lịch sinh hoạt, lịch tiếp SV (lịch sinh hoạt tổ chức 01 lần/1 tháng, lịch trực\n" +
                        "để tiếp sinh viên ít nhất 01 tiết/tuần), sinh hoạt điểm danh sinh viên, cập nhật thông tin sinh\n" +
                        "viên và lập biên bản sinh hoạt - BM.18B, BM.18C\n" +
                        "Bước 4: CVHT lập báo cáo hàng tháng tình hình công tác CVHT gửi về từng Chương trình đào\n" +
                        "tạo quản lý sinh viên - BM.18D\n" +
                        "Bước 5: Vào cuối học kỳ, CVHT phối hợp tổ chức đánh giá kết quả rèn luyện sinh viên, nhắc\n" +
                        "nhở, định hướng sinh viên lập kế hoạch học tập và đăng ký môn học cho học kỳ tiếp theo.\n" +
                        "Bước 6: Thư ký khoa tổng hợp báo cáo của từng CTĐT và gửi về cho phòng ĐTĐH 2 tháng/\n" +
                        "lần và cuối mỗi học kỳ - BM.18E\n" +
                        "5. Biểu mẫu kèm theo:\n" +
                        "\uF02D Danh sách phân công CVHT - BM.18A\n" +
                        "\uF02D Lịch sinh hoạt và tiếp sinh viên - BM.18B\n" +
                        "\uF02D Biên bản sinh hoạt lớp - BM.18C\n" +
                        "\uF02D Báo cáo tháng - BM.18D\n" +
                        "\uF02D Báo cáo cuối học kỳ - BM.18E\n",
                "TRƯỜNG ĐẠI HỌC THỦ DẦU MỘT\n" +
                        "KHOA:.......................\n" +
                        "CTĐT:.......................\n" +
                        "\n" +
                        "Số : / BC-........\n" +
                        "\n" +
                        "CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM\n" +
                        "\n" +
                        "Độc lập - Tự do - Hạnh phúc\n" +
                        "Bình Dương, ngày tháng năm\n\n"+
                        "BÁO CÁO CÔNG TÁC CỐ VẤN HỌC TẬP THÁNG....\n" +
                        "\n" +
                        "HỌC KỲ ......., NĂM HỌC 20... - 20...\n"+
                        "Họ và tên Cố vấn học tập:\n" +
                        "1-........................................................\n" +
                        "2-........................................................\n" +
                        "3-........................................................\n" +
                        "4-........................................................\n" +
                        "5-........................................................\n"+
                        "II. Công tác tư vấn trợ giúp sinh viên\n" +
                        "1. Số buổi thảo luận với sinh viên:............................................................\n" +
                        "2. Số sinh viên đăng ký không thành công (đăng ký qua mạng):.........................\n" +
                        "3. Số sinh viên xin hủy đăng ký học phần:................................................\n" +
                        "4. Số sinh viên đăng ký đề tài nghiên cứu khoa học:......................................\n" +
                        "5. Số sinh viên tham gia các hoạt động ngoại khóa đạt kết quả tốt:.....................\n" +
                        "6. Số sinh viên được nhắc nhở động viên:.................................................\n" +
                        "III. Công tác khác của cố vấn học tập\n" +
                        "1. Tham gia tập huấn:...........................................................................\n" +
                        "2. Phối hợp với các đơn vị giải quyết các vấn đề liên quan đến sinh viên:.............\n" +
                        "3. Tiếp nhận:................................................................................\n" +
                        "4. Bàn giao:.......................................................................................\n" +
                        "\n" +
                        "IV. Thuận lợi và khó khăn\n" +
                        "1.........................................................................................................\n" +
                        "2.........................................................................................................\n" +
                        "IV. Đề xuất và kiến nghị\n" +
                        "1.........................................................................................................\n" +
                        "2.........................................................................................................\n" +
                        "\n" +
                        "Giám đốc CTĐT Người tổng hợp"
        };
        List<String> lVB1= Arrays.asList(VB_1);
        List<String> lNDVB1= Arrays.asList(ND_VB_1);
        lVanBan=new ArrayList<>();
        for (int i=0;i<lVB1.size();i++){
            lVanBan.add(new VanBanCVHT(i+1, lVB1.get(i), lNDVB1.get(i)));
        }
        return lVanBan;
    }
    public static List<VanBanCVHT> getVB2(){
        return lVanBan;
    }
    public static List<VanBanCVHT> getVB3(){
        return lVanBan;
    }
}
