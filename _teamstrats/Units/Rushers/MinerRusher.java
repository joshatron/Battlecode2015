package _teamstrats.Units.Rushers;

import battlecode.common.*;
import _teamstrats.Unit;
import _teamstrats.Units.Miner;
import _teamstrats.Utilities;

public class MinerRusher extends Miner {
    public MinerRusher(RobotController rc)
    {
        super(rc);
    }

    public void collectData() throws GameActionException
    {
        super.collectData();

        target = Utilities.getRushLocation(rc);

        nav.setAvoidTowers(false);
        nav.setAvoidHQ(false);
    }

    public Unit getNewStrategy(Unit current) throws GameActionException
    {
        return current;
    }

    public boolean carryOutAbility() throws GameActionException
    {
        return false;
    }

    public boolean takeNextStep() throws GameActionException
    {
        if (target == null)
        {
            return false;
        }
        return nav.takeNextStep(target);
    }

    public boolean fight() throws GameActionException
    {
        return fighter.advancedFightMicro(nearByEnemies);
        //return fighter.basicFightMicro(nearByEnemies);
    }
}
