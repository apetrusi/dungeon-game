package com.example.myapplication.ViewModel;

import com.example.myapplication.Model.Enemy;
import com.example.myapplication.Model.Zombie;
import com.example.myapplication.Model.Fireball;
import com.example.myapplication.Model.Skeleton;
import com.example.myapplication.Model.GirlZombie;
import com.example.myapplication.Model.MapLayout;
import com.example.myapplication.R;

public class EnemyFactory {
     public static Enemy createEnemy(String type, MapLayout mapLayout) {
          if (type == null) {
               return null;
          }
          switch (type) {
               case "Zombie":
                    return new Zombie(0, 0, 20, 0.13f, R.drawable.skeleton2, mapLayout);
               case "Fireball":
                    return new Fireball(0, 0, 30, 0.13f, R.drawable.fireball, mapLayout);
               case "Skeleton":
                    return new Skeleton(0, 0, 25, 0.13f, R.drawable.skeleton, mapLayout);
               case "GirlZombie":
                    return new GirlZombie(0, 0, 35, 0.13f, R.drawable.girl_zombie, mapLayout);
               default:
                    throw new IllegalArgumentException("Invalid enemy type");
          }
     }

}
