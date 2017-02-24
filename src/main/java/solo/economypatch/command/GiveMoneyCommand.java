package solo.economypatch.command;

/*
 * EconomyAPI: Core of economy system for Nukkit
 * Copyright (C) 2016  onebone <jyc00410@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import me.onebone.economyapi.EconomyAPI;
import solo.solobasepackage.util.Message;
import solo.solobasepackage.util.Notification;
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class GiveMoneyCommand extends Command{
	
	public GiveMoneyCommand(){
		super("돈주기", "다른 플레이어에게 돈을 줍니다.", "/돈주기 [유저] [금액]", new String[]{"givemoney", "돈지급"});
		this.setPermission("economyapi.command.givemoney");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args){
		if(args.length < 2){
			Message.usage(sender, this.getUsage());
			return true;
		}
		String player = args[0];
		
		Player p = Server.getInstance().getPlayer(player);
		if(p != null){
			player = p.getName();
		}
		try{
			double amount = Double.parseDouble(args[1]);
			if(amount < 0){
				Message.alert(sender, "금액은 음수가 될 수 없습니다.");
				return true;
			}
			
			int result = EconomyAPI.getInstance().addMoney(player, amount);
			switch(result){
				case EconomyAPI.RET_INVALID:
					Message.alert(sender, Double.toString(amount) + " 은 최대 소유 가능한 돈을 넘습니다.");
					return true;
				case EconomyAPI.RET_NO_ACCOUNT:
					Message.alert(sender, player + "님은 서버에 접속한 적이 없습니다.");
					return true;
				case EconomyAPI.RET_SUCCESS:
					Message.normal(sender, player + "님에게 " + Double.toString(amount) + "원을 주셨습니다.");
					if(p instanceof Player){
						Notification.addNotification(p, sender.getName() + "님으로 부터 " + Double.toString(amount) + "원을 받으셨습니다.");
					}
					return true;
			}
		}catch(NumberFormatException e){
			Message.alert(sender, "금액은 숫자이어야 합니다.");
		}
		return true;
	}

}
