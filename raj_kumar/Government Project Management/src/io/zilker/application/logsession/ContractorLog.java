package io.zilker.application.logsession;

public class ContractorLog {
	public int CONTR_ID = 0;
	public String contractorName = null;
	public int getCONTR_ID() {
		return CONTR_ID;
	}

	public String getContractorName() {
		return contractorName;
	}

	public void setContractorName(String contractorName) {
		this.contractorName = contractorName;
	}

	public void setCONTR_ID(int cONTR_ID) {
		CONTR_ID = cONTR_ID;
	}
}
