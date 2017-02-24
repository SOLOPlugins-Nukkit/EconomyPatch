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

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

import me.onebone.economyapi.EconomyAPI;
import solo.solobasepackage.util.Message;

public class MyMoneyCommand extends Command{
	
	public MyMoneyCommand(){
		super("내돈", "내가 가지고 있는 돈을 봅니다.", "/내돈", new String[]{"mymoney", "돈"});
		this.setPermission("economyapi.command.mymoney");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(!(sender instanceof Player)){
			Message.alert(sender, "인게임에서만 사용 가능합니다.");
			return true;
		}
		Player player = (Player) sender;
		Message.normal(sender, player.getName() + "님의 돈 : " + Double.toString(EconomyAPI.getInstance().myMoney(player)));
		return true;
	}
}
