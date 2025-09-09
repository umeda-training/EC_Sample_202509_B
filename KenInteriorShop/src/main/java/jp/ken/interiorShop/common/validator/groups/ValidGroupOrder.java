package jp.ken.interiorShop.common.validator.groups;

import jakarta.validation.GroupSequence;

// 作成 : 西村
@GroupSequence({ValidGroup1.class, ValidGroup2.class, ValidGroup3.class})
public interface ValidGroupOrder {

}
