package com.app1.app1.product.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ThaiTitle {

    // คำนำหน้าชื่อสามัญ
    MR("นาย", "Mr.", Gender.MALE, 1),
    MRS("นาง", "Mrs.", Gender.FEMALE, 1),
    MISS("นางสาว", "Miss", Gender.FEMALE, 1),
    KHUN("คุณ", "Khun", Gender.UNSPECIFIED, 1),

    // เด็ก
    MASTER("ด.ช.", "Master", Gender.MALE, 2),
    MISS_CHILD("ด.ญ.", "Miss", Gender.FEMALE, 2),

    // วิชาการ
    DR("ดร.", "Dr.", Gender.UNSPECIFIED, 3),
    ASST_PROF("ผศ.", "Asst. Prof.", Gender.UNSPECIFIED, 3),
    ASSOC_PROF("รศ.", "Assoc. Prof.", Gender.UNSPECIFIED, 3),
    PROF("ศ.", "Prof.", Gender.UNSPECIFIED, 3),

    // ยศ
    GEN_ARMY("พล.อ.", "Gen. (Army)", Gender.MALE, 4),
    GEN_POLICE("พล.ต.อ.", "Pol. Gen.", Gender.MALE, 4);

    private final String thai;
    private final String english;
    private final Gender gender;
    private final int group;

    public static ThaiTitle fromThai(String thaiTitle) {
        for (ThaiTitle t : values()) {
            if (t.thai.equals(thaiTitle)) {
                return t;
            }
        }
        return null;
    }

    public static ThaiTitle fromEnglish(String engTitle) {
        for (ThaiTitle t : values()) {
            if (t.english.equalsIgnoreCase(engTitle)) {
                return t;
            }
        }
        return null;
    }

    public enum Gender {
        MALE, FEMALE, UNSPECIFIED
    }
}
