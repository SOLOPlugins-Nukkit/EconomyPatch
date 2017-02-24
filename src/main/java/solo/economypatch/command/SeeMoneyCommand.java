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
import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class SeeMoneyCommand extends Command{
	
	public SeeMoneyCommand(){
		super("돈보기", "다른 유저의 돈을 확인합니다.", "/돈보기 [유저]", new String[]{"돈확인", "seemoney"});
		this.setPermission("economyapi.command.seemoney");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args){	
		if(args.length < 1){
			Message.usage(sender, this.getUsage());
			return true;
		}
		
		String player = args[0];
		Player p = Server.getInstance().getPlayer(player);
		if(p != null){
			player = p.getName();
		}
		
		double money = EconomyAPI.getInstance().myMoney(player);
		if(money < 0){
			Message.alert(sender, player + "님은 서버에 접속한 적이 없습니다.");
			return true;
		}
		Message.normal(sender, player + "님의 돈 : " + Double.toString(money));
		return true;
	}
}
