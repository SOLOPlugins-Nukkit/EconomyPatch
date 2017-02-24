package solo.economypatch;

import cn.nukkit.plugin.PluginBase;
import solo.economypatch.command.*;
import solo.solobasepackage.util.CommandUtil;

public class Main extends PluginBase{
	
	@Override
	public void onEnable(){
		CommandUtil.unregisterCommand("givemoney");
		CommandUtil.unregisterCommand("mymoney");
		CommandUtil.unregisterCommand("pay");
		CommandUtil.unregisterCommand("seemoney");
		CommandUtil.unregisterCommand("setlang");
		CommandUtil.unregisterCommand("setmoney");
		CommandUtil.unregisterCommand("takemoney");
		CommandUtil.unregisterCommand("topmoney");
		
		CommandUtil.registerCommand(new GiveMoneyCommand());
		CommandUtil.registerCommand(new MyMoneyCommand());
		CommandUtil.registerCommand(new PayCommand());
		CommandUtil.registerCommand(new SeeMoneyCommand());
		CommandUtil.registerCommand(new SetMoneyCommand());
		CommandUtil.registerCommand(new TakeMoneyCommand());
		CommandUtil.registerCommand(new TopMoneyCommand());
	}
}