package _teamSoldierTank.Units.Defenders;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;
import _teamSoldierTank.Units.Basher;
import _teamSoldierTank.Units.DefensiveUnits;
import _teamSoldierTank.*;
import _teamSoldierTank.Units.Rushers.BasherRusher;

public class DefensiveBasher extends DefensiveUnits
{
    public DefensiveBasher(RobotController rc)
    {
        super(rc);

        nav.setAvoidHQ(true);
        nav.setAvoidTowers(true);
    }

    public void collectData2() throws GameActionException
    {
        target = Utilities.getTowerClosestToEnemyHQ(rc);


        rc.setIndicatorString(1, "Target: " + target);
    }

    public boolean fight() throws GameActionException
    {
        return fighter.basherFightMicro();
    }

    public Unit getNewStrategy(Unit current) throws GameActionException
    {
        // change to base Basher when it is time to attack
        if (rc.readBroadcast(Messaging.Attack.ordinal()) == 1)
        {
            return new BasherRusher(rc);
        }
        return current;
    }
}
