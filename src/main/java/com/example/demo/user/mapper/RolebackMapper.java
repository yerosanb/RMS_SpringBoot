package com.example.demo.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RolebackMapper {
	@Select("select count(ic.file_id) from issue_core ic where ic.file_id=#{id}")
	int issue_core(long id);
	@Select("select count(iq.file_id) from issue_qbs iq where iq.file_id=#{id}")
	int issue_qbs(long id);
	@Update("update data_issue_core set availability='0' where  file_id=#{id}")
	int roleback_issue_core_file(long id);
	@Update("update data_issue_qbs set availability='0' where file_id=#{id}")
	int roleback_issue_qbs_file(long id);
	@Select("select count(pc.file_id) from payable_credit pc where pc.file_id=#{id}")
	int payableCredit(long id);
	
	@Select("select count(rc.file_id) from receivable_credit rc where rc.file_id=#{id}")
	int receivableCredit(long id);

	@Select("select count(rd.file_id) from receivable_debit rd where rd.file_id=#{id}")
	int receivableDebit(long id);
	
	@Select("select count(pd.file_id) from payable_debit pd where pd.file_id=#{id}")
	int payableDebit(long id);
	
	@Select("select count(rna.file_id) from rtgs_nbe_ats rna where rna.file_id=#{id}")
	int rtgsam(long id);

	@Select("select count(rac.file_id) from rtgs_awb_core rac where rac.file_id=#{id}")
	int rtgcm(long id);

	@Select("select count(ena.file_id) from erca_nbe_ats ena where ena.file_id=#{id}")
	int ercam(long id);

	@Select("select count(eac.file_id) from erca_awb_core eac where eac.file_id=#{id}")
	int erccm(long id);

	@Select("select count(bna.file_id) from b2b_nbe_ats bna where bna.file_id=#{id}")
	int btbam(long id);

	@Select("select count(bac.file_id) from b2b_awb_core bac where bac.file_id=#{id}")
	int btbcm(long id);

	@Select("select count(sna.file_id) from sos_nbe_ats sna where sna.file_id=#{id}")
	int sosam(long id);

	@Select("select count(sac.file_id) from sos_awb_core sac where sac.file_id=#{id}")
	int soscm(long id);

	@Select("select count(apm.file_id) from ats_partially_matched apm where apm.file_id=#{id}")
	int apm(long id);

	@Select("select count(cpm.file_id) from core_partially_matched cpm where cpm.file_id=#{id}")
	int cpm(long id);
	@Update("update data_nbe_ats set availability='0' where  file_id=#{id}")
	int roleback_ats_file(long id);
	@Update("update data_payable set availability='0' where  file_id=#{id}")
	int roleback_payable_file(long id);
	@Update("update data_recivable set availability='0' where  file_id=#{id}")
	int roleback_receivable_file(long id);
	@Update("update data_awb_core set availability='0' where file_id=#{id}")
	int roleback_core_file(long id);
	@Update("update files set availability='0' where id=#{id}")
	int roleback_file(long id);
}
