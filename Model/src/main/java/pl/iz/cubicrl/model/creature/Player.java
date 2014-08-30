/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iz.cubicrl.model.creature;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.Arrays;
import javax.validation.constraints.NotNull;
import pl.iz.cubicrl.model.util.PropertyLoader;
/*@param name of the creature, must be unique
 * @param attri initial values of attributes
 * [STRNGTH,DXTRT,SPD,CNST,INTL,CHA]
 * @param sklls initial values of skills
 * [DDG,THRWNG,PRJCTL,SPCH,MATH,SNEAK]
 * @param lifeStatsLimits initial values of lifeStats
 * [MAXHP,MAXSAN,MAXHUNGER,MAXTHRST]
 * @param rsistancs initial values of resistances
 * [BLNT,PRCNG,SLSHNG,HT,CLD,CHMCL,PSCHC]
 * @param secondaries value of secondary stats [AC]
 */

/**
 *
 * @author Ivo
 */
@Singleton
public class Player extends HumanoidCreature {

	@Inject
	public Player(CreatureStats stats) {
		super(stats.defaultName, stats.attributes, stats.skills, stats.lifestats, stats.resistances, stats.secondaries);
	}

}
