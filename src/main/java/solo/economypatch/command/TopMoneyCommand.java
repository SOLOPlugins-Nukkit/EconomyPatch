package solo.economypatch.command;

import java.util.ArrayList;

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

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import me.onebone.economyapi.EconomyAPI;
import solo.solobasepackage.util.Message;

public class TopMoneyCommand extends Command{
	
	public TopMoneyCommand(){
		super("돈순위", "돈 순위를 보여줍니다.", "/돈순위 [페이지]", new String[]{"topmoney"});
		this.setPermission("economyapi.command.topmoney");
	}

	@Override
	public boolean execute(final CommandSender sender, String label, final String[] args){
		try{
			final LinkedHashMap<String, Double> money = EconomyAPI.getInstance().getAllMoney();
			final int page = args.length > 0 ? Math.max(1, Integer.parseInt(args[0])) : 1;
			new Thread(){
				public void run(){
					List<String> list = new LinkedList<>();
					for(String player : money.keySet()) list.add(player);
					
					Collections.sort(list, new Comparator<String>(){
						@Override
						public int compare(String s1, String s2) {
							double one = money.get(s1);
							double two = money.get(s2);
							return one < two ? 1 : one > two ? -1 : 0;
						}
					});
					
					ArrayList<String> output = new ArrayList<>();
					
					int duplicate = 0;
					double prev = -1D;
					for(int n = 0; n < list.size(); n++){
						double m = money.get(list.get(n));
						if(m == prev){
							duplicate++;
						}else{
							duplicate = 0;
						}
						prev = m;
						output.add("§a[" + Integer.toString(n + 1 - duplicate) + "] " + list.get(n) + "§f : " + Double.toString(m) + "원");
					}
					//output.substring(0, output.length() - 1);
					
					if(sender != null){
						Message.page(sender, "돈 순위", output, page);
					}
				}
			}.start();
		}catch(NumberFormatException e){
			Message.alert(sender, "페이지는 숫자로 적어주세요.");
		}
		return true;
	}

}
