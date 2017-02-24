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

import solo.solobasepackage.util.Message;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class SetLangCommand extends Command{
	
	public SetLangCommand(){
		super("언어설정", "언어를 설정합니다.", "/언어설정 <ccTLD>", new String[]{"setlang"});
		this.setPermission("economyapi.command.setlang");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(args.length < 1){
			Message.usage(sender, this.getUsage());
			return true;
		}
		//String lang = args[0];
		
		// TODO
		return true;
	}
}
