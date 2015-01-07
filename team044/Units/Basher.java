package team044.Units;


import team044.FightMicro;
import team044.Navigator;
import team044.Unit;

import battlecode.common.*;
import team044.Utilities;

public class Basher extends Unit
{
    FightMicro fighter;
    MapLocation target;
    Navigator nav;
    public Basher(RobotController rc)
    {
        this.rc = rc;
        nav = new Navigator(rc);
        fighter = new FightMicro(rc);
        range = rc.getType().attackRadiusSquared;
        us = rc.getTeam();
        opponent = us.opponent();
        target = Utilities.getTowerClosestToEnemyHQ(rc);
    }

    public void collectData()
    {
        // collect our data
        MapLocation[] enemyTower = rc.senseEnemyTowerLocations();
        if (Clock.getRoundNum() > 1000 && enemyTower.length > 0)
        {
            target = enemyTower[0];
        }
        else if (Clock.getRoundNum() > 1500)
        {
            target = rc.senseEnemyHQLocation();
        }
    }

    public void handleMessages() throws GameActionException
    {
        // default to doing nothing
    }

    public boolean takeNextStep() throws GameActionException
    {
        return nav.takeNextStep(target);
    }

    public boolean fight() throws GameActionException
    {
        return false;
    }

    public Unit getNewStrategy(Unit current) throws GameActionException
    {
        return current;
    }

    public boolean carryOutAbility() throws GameActionException
    {
        return false;
    }
}