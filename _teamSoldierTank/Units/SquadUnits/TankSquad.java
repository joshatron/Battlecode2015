package _teamSoldierTank.Units.SquadUnits;

import battlecode.common.*;
import _teamSoldierTank.Messaging;
import _teamSoldierTank.Unit;
import _teamSoldierTank.Units.Rushers.LauncherRusher;
import _teamSoldierTank.Units.Rushers.TankRusher;
import _teamSoldierTank.Units.SquadUnit;

public class TankSquad extends SquadUnit
{
    public TankSquad(RobotController rc) throws GameActionException
    {
        super(rc);
        group = rc.readBroadcast(Messaging.TankGroup.ordinal());
        rc.broadcast(Messaging.TankGroup.ordinal(), -1);
        rc.setIndicatorString(0, "Squad Tank group:" + group);
    }

    public void collectData() throws GameActionException
    {
        super.collectData();

        if (group < 1)
        {
            group = rc.readBroadcast(Messaging.TankGroup.ordinal());
            rc.broadcast(Messaging.TankGroup.ordinal(), -1);
            rc.setIndicatorString(0, "Squad Tank group:" + group);
        }
    }

    public Unit getNewStrategy(Unit current) throws GameActionException
    {
        if (rc.readBroadcast(Messaging.RushEnemyBase.ordinal()) == 1)
        {
            return new TankRusher(rc);
        }
        return current;
    }
}
