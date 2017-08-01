package com.sumo.traffic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;

import com.sumo.traffic.InfoOfPlaces.InfoOfArt;
import com.sumo.traffic.InfoOfPlaces.InfoOfAteneo;
import com.sumo.traffic.InfoOfPlaces.InfoOfBayani;
import com.sumo.traffic.InfoOfPlaces.InfoOfCOF;
import com.sumo.traffic.InfoOfPlaces.InfoOfDam;
import com.sumo.traffic.InfoOfPlaces.InfoOfEast;
import com.sumo.traffic.InfoOfPlaces.InfoOfEdsa;
import com.sumo.traffic.InfoOfPlaces.InfoOfMaginhawa;
import com.sumo.traffic.InfoOfPlaces.InfoOfNinoy;
import com.sumo.traffic.InfoOfPlaces.InfoOfParish;
import com.sumo.traffic.InfoOfPlaces.InfoOfPeople;
import com.sumo.traffic.InfoOfPlaces.InfoOfQmc;
import com.sumo.traffic.InfoOfPlaces.InfoOfUp;
import com.sumo.traffic.InfoOfPlaces.InfoOfVargas;
import com.sumo.traffic.InfoOfPlaces.InfoOfWatershed;

import java.util.ArrayList;
import java.util.List;

public class ReviewChoiceOfPlace extends AppCompatActivity {

    static RecyclerView recyclerViewStaff;
    static RecyclerView.Adapter adapterStaff;
    static List<placeitem> InitialListStaffs;


    int bayani = 0;
    int cof = 0;
    int dam = 0;
    int east = 0;
    int edsa = 0;
    int maginhawa = 0;
    int ninoy = 0;
    int parish = 0;
    int people = 0;
    int qcx = 0;
    int qmc = 0;
    int up = 0;
    int vargas = 0;
    int watershed = 0;
    int wildlife = 0;
int art = 0;
    int ayala = 0;
    int ateneo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_choice_of_place);

        recyclerViewStaff = (RecyclerView) findViewById(R.id.recyclerViewStaff);

        placeitem turnk = new placeitem();

        recyclerViewStaff.setHasFixedSize(true);
        recyclerViewStaff.setLayoutManager(new LinearLayoutManager(this));
        InitialListStaffs = new ArrayList<>();




        if (InfoOfUp.select == 1) {
            if (ayala == 0) {

                ayala = 1;
            } else if (ayala == 1) {

            }
        }

        if (InfoOfArt.select == 1) {
            if (art == 0) {


                art = 1;

            } else if (art == 1) {

            }
        }

        if (InfoOfParish.select == 1) {
            if (parish == 0) {

                parish = 1;

            } else if (parish == 1) {

            }
        }


        if (InfoOfAteneo.select == 1) {

            if (ateneo == 0) {

                ateneo = 1;
            } else if (ateneo == 1) {

            }

        }
        if (InfoOfBayani.select == 1) {
            if (bayani == 0) {

                bayani = 1;
            } else if (bayani == 1) {

            }

        }

        if (InfoOfCOF.select == 1) {

            if (cof == 0) {

                cof = 1;
            } else if (cof == 1) {

            }
        }

        if (InfoOfDam.select == 1) {

            if (dam == 0) {

                dam = 1;
            } else if (dam == 1) {

            }
        }


        if (InfoOfEast.select == 1) {

            if (east == 0) {

                east = 1;
            } else if (east == 1) {

            }
        }


        if (InfoOfEdsa.select == 1) {

            if (edsa == 0) {

                edsa = 1;
            } else if (edsa == 1) {

            }

        }

        if (InfoOfMaginhawa.select == 1) {


            if (maginhawa == 0) {

                maginhawa = 1;
            } else if (maginhawa == 1) {

            }
        }


        if (InfoOfNinoy.select == 1) {


            if (ninoy == 0) {

                ninoy = 1;
            } else if (ninoy == 1) {

            }

        }

        if (InfoOfPeople.select == 1) {


            if (people == 0) {

                people = 1;
            } else if (people == 1) {

            }

        }


        if (InfoOfQmc.select == 1) {


            if (qmc == 0) {


                qmc = 1;


            } else if (qmc == 1) {

            }

        }

        if (InfoOfVargas.select == 1) {


            if (vargas == 0) {

                vargas = 1;
            } else if (vargas == 1) {

            }

        }
        if (InfoOfWatershed.select == 1) {


            if (watershed == 0) {

                watershed = 1;
            } else if (watershed == 1) {

            }

        }
        turnk.setname("TEST");
        turnk.settype("TEST");

        InitialListStaffs.add(turnk);

        adapterStaff = new ReviewChoiceOfPlaceAdapter(InitialListStaffs, getApplicationContext());

        recyclerViewStaff.setAdapter(adapterStaff);
    }





}
