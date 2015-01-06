package theSimpleSoldier;

import battlecode.common.*;

public class HQ extends Structure
{
    RobotController rc;
    RobotInfo[] enemies;
    RobotInfo[] nearByEnemies;
    RobotInfo[] allies;
    RobotInfo[] nearByAllies;
    int range;
    Team us;
    Team opponent;
    int numbOfSpawnedSoldiers = 0;
    Direction[] dirs;
    FightMicro fighter;

    public HQ(RobotController rc)
    {
        this.rc = rc;
        range = rc.getType().attackRadiusSquared;
        us = rc.getTeam();
        opponent = us.opponent();
        fighter = new FightMicro(rc);
    }

    public void handleMessages() throws GameActionException
    {
        rc.broadcast(Messaging.BuildOrder.ordinal(), BuildOrderMessaging.BuildBaracks.ordinal());
        if (numbOfSpawnedSoldiers % 5 == 1)
        {
            rc.broadcast(Messaging.BeaverType.ordinal(), BuildOrderMessaging.BuildBeaverBuilder.ordinal());
        }
        else
        {
            rc.broadcast(Messaging.BeaverType.ordinal(), BuildOrderMessaging.BuildBeaverMiner.ordinal());
        }
    }

    public void collectData() throws GameActionException
    {
        enemies = rc.senseNearbyRobots(99999, opponent);
        nearByEnemies = rc.senseNearbyRobots(range, opponent);
        allies = rc.senseNearbyRobots(99999, us);
        nearByAllies = rc.senseNearbyRobots(range, us);
    }

    public boolean fight() throws GameActionException
    {
        if (rc.isWeaponReady())
        {
            return fighter.structureFightMicro(nearByEnemies);
        }
        return false;
    }

    public boolean carryOutAbility() throws GameActionException
    {
        if (nearByEnemies.length > 0)
        {
            System.out.println("Near by enemies length to great");
            return false;
        }
        if (Utilities.spawnUnit(RobotType.BEAVER, rc))
        {
            numbOfSpawnedSoldiers++;
            return true;
        }
        return false;
    }
}
