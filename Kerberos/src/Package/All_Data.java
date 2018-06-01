package Package;

public class All_Data {
	private byte control;
	private Data_Regist register;
	private Data_Modify modify;
	private Data_Online online;
	private Data_Offline offline;
	private Data_Chat chat;
	private Data_Land land;
	public void setControl(byte control){
		this.control=control;
	}
	public void setData_Regist(Data_Regist register){
		this.register=register;
	}
	public void setData_Modify(Data_Modify modify){
		this.modify=modify;
	}
	public void setData_Online(Data_Online online){
		this.online=online;
	}
	public void setData_Offline(Data_Offline offline){
		this.offline=offline;
	}
	public void setData_Chat(Data_Chat chat){
		this.chat=chat;
	}
	public void setData_Land(Data_Land land){
		this.land=land;
	}
	public byte getControl(){
		return control;
	}
	public Data_Regist getData_Regist(){
		return register;
	}
	public Data_Modify getData_Modify(){
		return modify;
	}
	public Data_Online getData_Online(){
		return online;
	}
	public Data_Offline getData_Offline(){
		return offline;
	}
	public Data_Chat getData_Chat(){
		return chat;
	}
	public Data_Land getData_Land(){
		return land;
	}
}
