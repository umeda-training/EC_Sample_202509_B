package jp.ken.interiorShop.common.validator.groups;

import jakarta.validation.GroupSequence;

@GroupSequence({ValidGroup1.class, ValidGroup2.class})
public interface ValidGroupOrder {

}
