package com.alimaddi.sample;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
import com.alimaddi.sample.databinding.ActivityMainBinding;
import com.alimaddi.segmentedbutton.SegmentedButton;
import com.alimaddi.segmentedbutton.SegmentedButtonGroup;
import com.alimaddi.sample.drawable.BadgeDrawable;
import java.util.ArrayList;
import java.util.Random;

enum Action
{
    None,
    ChangeBorder1,
    ChangeBorder2,
    ChangeBackgroundColor,
    ChangeBackgroundDrawable,
    ChangeRadius,
    ChangePositionAnimated,
    ChangePosition,
    ToggleDraggable,
    ToggleRipple,
    ToggleRippleColor,
    ChangeDivider,
    ChangeAnimation,
    RemoveButtonDrawable,
    ChangeButtonDrawable,
    ChangeDrawableTint,
    ToggleDrawableSize,
    ChangeDrawableGravity,
    ChangeText,
    ChangeTextColor,
    ChangeTextSize,
    ChangeTypeface,
    ChangeSelectedButtonRadius,
    ChangeSelectedButtonBorderSolid,
    ChangeSelectedButtonBorderDashed,
    ToggleHiddenButtons,
    ChangeTextSize2;

    public String getDisplayText()
    {
        if (this == None)
            return "";
        else if (this == ChangeBorder1)
            return "Change border 1";
        else if (this == ChangeBorder2)
            return "Change border 2";
        else if (this == ChangeBackgroundColor)
            return "Change background and selected background to color";
        else if (this == ChangeBackgroundDrawable)
            return "Change background and selected background to drawable";
        else if (this == ChangeRadius)
            return "Change radius";
        else if (this == ChangePositionAnimated)
            return "Change position and animate movement";
        else if (this == ChangePosition)
            return "Change position without animating";
        else if (this == ToggleDraggable)
            return "Toggle draggable boolean";
        else if (this == ToggleRipple)
            return "Toggle ripple";
        else if (this == ToggleRippleColor)
            return "Toggle ripple color between black/white";
        else if (this == ChangeDivider)
            return "Change divider";
        else if (this == ChangeAnimation)
            return "Change animation duration & interpolator";
        else if (this == RemoveButtonDrawable)
            return "Remove button drawable";
        else if (this == ChangeButtonDrawable)
            return "Change button drawable";
        else if (this == ChangeDrawableTint)
            return "Change drawable tint color";
        else if (this == ToggleDrawableSize)
            return "Toggle drawable size";
        else if (this == ChangeDrawableGravity)
            return "Change drawable gravity";
        else if (this == ChangeText)
            return "Change text";
        else if (this == ChangeTextColor)
            return "Change text color";
        else if (this == ChangeTextSize)
            return "Change text size";
        else if (this == ChangeTypeface)
            return "Change typeface";
        else if (this == ChangeSelectedButtonRadius)
            return "Change selected button radius";
        else if (this == ChangeSelectedButtonBorderSolid)
            return "Change selected button border (solid)";
        else if (this == ChangeSelectedButtonBorderDashed)
            return "Change selected button border (dashed)";
        else if (this == ToggleHiddenButtons)
            return "Toggle hidden buttons";
        else if (this == ChangeTextSize2)
            return "Change Text Size 2";
        else
            return "";
    }
}

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener
{
    private static final String TAG = "SegmentedButtonSample";
    private ActivityMainBinding binding;

//    @BindView(R.id.linearLayout)
//    LinearLayout linearLayout;
//    @BindView(R.id.spinner)
//    Spinner spinner;
//    @BindView(R.id.button_changePosition)
//    Button changePositionButton;
//    @BindView(R.id.buttonGroup_gradient)
//    SegmentedButtonGroup gradientButtonGroup;
//    @BindView(R.id.buttonGroup_lordOfTheRings)
//    SegmentedButtonGroup lordOfTheRingsButtonGroup;
//    @BindView(R.id.buttonGroup_DCSuperheros)
//    SegmentedButtonGroup DCSuperHerosButtonGroup;
//    @BindView(R.id.buttonGroup_marvelSuperheros)
//    SegmentedButtonGroup marvelSuperherosButtonGroup;
//    @BindView(R.id.buttonGroup_guys)
//    SegmentedButtonGroup guysButtonGroup;
//    @BindView(R.id.buttonGroup_starWars)
//    SegmentedButtonGroup starWarsButtonGroup;
//    @BindView(R.id.buttonGroup_darthVader)
//    SegmentedButtonGroup darthVaderButtonGroup;
//    @BindView(R.id.buttonGroup_draggable)
//    SegmentedButtonGroup draggableButtonGroup;
//    @BindView(R.id.buttonGroup_dynamic)
//    SegmentedButtonGroup dynamicButtonGroup;
//    @BindView(R.id.button_left)
//    SegmentedButton leftButton;
//    @BindView(R.id.button_right)
//    SegmentedButton rightButton;
//    @BindView(R.id.buttonGroup_pickupDropoffBoth)
//    SegmentedButtonGroup pickupDropoffButtonGroup;
//    @BindView(R.id.buttonGroup_starWarsAlt)
//    SegmentedButtonGroup starWarsAltButtonGroup;
//    @BindView(R.id.buttonGroup_roundSelectedButton)
//    SegmentedButtonGroup roundSelectedButtonGroup;

    SegmentedButtonGroup yesNoMaybePButtonGroup;
    SegmentedButtonGroup starWarsPButtonGroup;
    SegmentedButtonGroup countPButtonGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ArrayList<String> spinnerItems = new ArrayList<>();

        for (Action action : Action.values())
        {
            spinnerItems.add(action.getDisplayText());
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(this);

        updateButton(binding.buttonGroupGradient.getPosition());
        binding.buttonGroupGradient.setOnPositionChangedListener(position -> updateButton(position));

        setupDynamicDrawables();
        setupYesNoMaybePButtonGroup();
        setupStarWarsPButtonGroup();
        setupCountPButtonGroup();

        // Basic checks
        if (binding.buttonGroupStarWars.getButtons().size() != 3)
        {
            throw new AssertionError("Buttons size incorrect");
        }

        if (!binding.buttonGroupLordOfTheRings.getButton(1).getText().equals("Gimli"))
        {
            throw new AssertionError("Button name is incorrect");
        }

        binding.buttonChangePosition.setOnClickListener(view -> {
            int position = binding.buttonGroupGradient.getPosition();
            position = ++position % 3;
            updateButton(position);

            binding.buttonGroupGradient.setPosition(position, true);
        });
    }

    @Override
    public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id)
    {
        Action action = Action.values()[position];

        switch (action)
        {
            case None:
                break;

            case ChangeBorder1:
                binding.buttonGroupLordOfTheRings.setBorder(5, Color.RED, 25, 8);
                binding.buttonGroupMarvelSuperheros.setBorder(5, Color.BLACK, 30, 10);
                break;

            case ChangeBorder2:
                binding.buttonGroupLordOfTheRings.setBorder(2, Color.RED, 25, 8);
                binding.buttonGroupMarvelSuperheros.setBorder(2, Color.BLACK, 30, 10);
                break;

            case ChangeBackgroundColor:
                binding.buttonGroupLordOfTheRings.setBackground(ContextCompat.getColor(getApplicationContext(),
                                                                               R.color.brown_600));
                binding.buttonGroupLordOfTheRings.setSelectedBackground(ContextCompat.getColor(getApplicationContext(),
                                                                                       R.color.blue_800));
                break;

            case ChangeBackgroundDrawable:
                binding.buttonGroupLordOfTheRings.setBackground(ContextCompat.getDrawable(getApplicationContext(),
                                                                                  R.drawable.gradient_drawable));
                binding.buttonGroupLordOfTheRings.setSelectedBackground(ContextCompat.getDrawable(getApplicationContext(),
                                                                                          R.drawable.gradient_drawable_selector));
                break;

            case ChangeRadius:
                binding.buttonGroupMarvelSuperheros.setRadius(5);
                binding.buttonGroupDCSuperheros.setRadius(50);
                break;

            case ChangePositionAnimated:
                binding.buttonGroupLordOfTheRings.setPosition(((binding.buttonGroupLordOfTheRings.getPosition() + 1) % 3), true);
                break;

            case ChangePosition:
                binding.buttonGroupLordOfTheRings.setPosition(((binding.buttonGroupLordOfTheRings.getPosition() + 1) % 3), false);
                break;

            case ToggleDraggable:
                binding.buttonGroupDarthVader.setDraggable(!binding.buttonGroupDarthVader.isDraggable());
                break;

            case ToggleRipple:
                binding.buttonGroupStarWars.setRipple(!binding.buttonGroupStarWars.hasRipple());
                break;

            case ToggleRippleColor:
                binding.buttonGroupStarWars.setRipple(binding.buttonGroupStarWars.getRippleColor() == Color.WHITE ? Color.BLACK :
                                                      Color.WHITE);
                break;

            case ChangeDivider:
                binding.buttonGroupLordOfTheRings.setDivider(Color.MAGENTA, 10, 10, 10);
                binding.buttonGroupGuys.setDivider(ContextCompat.getDrawable(getApplicationContext(),
                                                                     R.drawable.gradient_drawable_divider), 20, 0, 0);
                break;

            case ChangeAnimation:
                binding.buttonGroupLordOfTheRings.setSelectionAnimationDuration(
                        binding.buttonGroupLordOfTheRings.getSelectionAnimationDuration() == 500 ? 5000 : 500);

                final int random = new Random().nextInt(12);
                binding.buttonGroupLordOfTheRings.setSelectionAnimationInterpolator(random);
                break;

            case RemoveButtonDrawable:
                binding.buttonGroupLordOfTheRings.getButton(0).setDrawable(null);
                break;

            case ChangeButtonDrawable:
                binding.buttonGroupLordOfTheRings.getButton(0).setDrawable(ContextCompat.getDrawable(getApplicationContext(),
                                                                                             R.drawable.ic_b9));
                binding.buttonGroupDCSuperheros.getButton(1).setDrawable(ContextCompat.getDrawable(getApplicationContext(),
                                                                                           R.drawable.ic_aragorn));
                break;

            case ChangeDrawableTint:
                binding.buttonGroupLordOfTheRings.getButton(0).setSelectedDrawableTint(Color.RED);
                binding.buttonGroupLordOfTheRings.getButton(1).setDrawableTint(Color.GREEN);
                binding.buttonGroupLordOfTheRings.getButton(2).setDrawableTint(Color.BLUE);
                binding.buttonGroupDarthVader.getButton(1).removeDrawableTint();
                break;

            case ToggleDrawableSize:
                final boolean setSize = !binding.buttonGroupDCSuperheros.getButton(0).hasDrawableWidth();
                final float size = dpToPx(96);

                for (SegmentedButton button : binding.buttonGroupDCSuperheros.getButtons())
                {
                    button.setDrawableWidth(setSize ? (int)size : -1);
                    button.setDrawableHeight(setSize ? (int)size : -1);
                }
                break;

            case ChangeDrawableGravity:
                binding.buttonGroupGradient.getButton(0).setDrawableGravity(Gravity.START);
                binding.buttonGroupStarWars.getButton(0).setDrawableGravity(Gravity.END);

                int currentGravity = binding.buttonGroupDCSuperheros.getButton(0).getDrawableGravity();

                switch (currentGravity)
                {
                    case Gravity.LEFT:
                        currentGravity = Gravity.TOP;
                        break;
                    case Gravity.TOP:
                        currentGravity = Gravity.RIGHT;
                        break;
                    case Gravity.RIGHT:
                        currentGravity = Gravity.BOTTOM;
                        break;
                    case Gravity.BOTTOM:
                        currentGravity = Gravity.LEFT;
                        break;
                }

                binding.buttonGroupDCSuperheros.getButton(0).setDrawableGravity(currentGravity);
                break;

            case ChangeText:
                binding.buttonGroupLordOfTheRings.getButton(0).setText("Testing");
                binding.buttonGroupLordOfTheRings.getButton(1).setText("");
                break;

            case ChangeTextColor:
                binding.buttonGroupLordOfTheRings.getButton(0).setSelectedTextColor(Color.RED);
                binding.buttonGroupLordOfTheRings.getButton(1).setTextColor(Color.BLUE);
                binding.buttonGroupGradient.getButton(1).removeSelectedTextColor();
                break;

            case ChangeTextSize:
                binding.buttonGroupLordOfTheRings.getButton(1).setTextSize(48.0f);
                binding.buttonGroupDCSuperheros.getButton(0).setTextSize(48.0f);
                break;

            case ChangeTypeface:
                binding.buttonGroupLordOfTheRings.getButton(0).setTextTypeface(Typeface.create((Typeface)null, Typeface.BOLD));
                break;

            case ChangeSelectedButtonRadius:
            {
                final float radius = dpToPx(30);

                binding.buttonGroupLordOfTheRings.setSelectedButtonRadius((int)radius);
                binding.buttonGroupRoundSelectedButton.setSelectedButtonRadius(0);
            }
            break;

            case ChangeSelectedButtonBorderSolid:
                binding.buttonGroupLordOfTheRings.setSelectedBorder(5, Color.MAGENTA, 0, 0);
                binding.buttonGroupRoundSelectedButton.setSelectedBorder(0, 0, 0, 0);
                break;

            case ChangeSelectedButtonBorderDashed:
                binding.buttonGroupLordOfTheRings.setSelectedBorder(5, Color.MAGENTA, 6, 3);
                binding.buttonGroupRoundSelectedButton.setSelectedBorder(16, Color.BLACK, 6, 2);
                break;

            case ToggleHiddenButtons:
            {
                SegmentedButton button = binding.buttonGroupGradient.getButton(0);
                button.setVisibility(button.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

                button = binding.buttonGroupStarWars.getButton(1);
                button.setVisibility(button.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

                button = binding.buttonGroupDarthVader.getButton(2);
                button.setVisibility(button.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

                button = binding.buttonGroupDraggable.getButton(0);
                button.setVisibility(button.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);

                button = binding.buttonGroupRoundSelectedButton.getButton(2);
                button.setVisibility(button.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
            break;

            case ChangeTextSize2:
                yesNoMaybePButtonGroup.getButton(0).setTextSize(dpToPx(36));
                yesNoMaybePButtonGroup.getButton(1).setTextSize(dpToPx(36));
                yesNoMaybePButtonGroup.getButton(2).setTextSize(dpToPx(36));
                break;

            default:
                break;
        }

        // Reset back to the normal value
        binding.spinner.setSelection(0);
    }

    @Override
    public void onNothingSelected(final AdapterView<?> parent)
    {

    }

    private void setupDynamicDrawables()
    {
        final BadgeDrawable drawable = new BadgeDrawable(Color.RED, 80, 50, 3, 3);
        binding.buttonLeft.setDrawable(drawable);

        binding.buttonGroupDynamic.setOnPositionChangedListener(position -> {
            if (position == 0)
            {
                drawable.setCount(drawable.getCount() + 1);
            }
        });

        final Drawable drawable2 = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_b1);
        binding.buttonRight.setDrawable(drawable2);
    }

    private void updateButton(int position)
    {
        binding.buttonChangePosition.setText("Position: " + position);
    }

    private float dpToPx(float dp)
    {
        return dp * getResources().getDisplayMetrics().density;
    }

    private float pxToDp(float px)
    {
        return px / getResources().getDisplayMetrics().density;
    }

    private float spToPx(float sp)
    {
        return sp * getResources().getDisplayMetrics().scaledDensity;
    }

    private float pxToSp(float px)
    {
        return px / getResources().getDisplayMetrics().scaledDensity;
    }

    private void setupYesNoMaybePButtonGroup()
    {
        SegmentedButtonGroup buttonGroup = new SegmentedButtonGroup(this);
        buttonGroup.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        ((LayoutParams)buttonGroup.getLayoutParams()).setMargins((int)dpToPx(4), (int)dpToPx(4),
                                                                 (int)dpToPx(4), (int)dpToPx(4));
        buttonGroup.setElevation(dpToPx(2));
        buttonGroup.setBackground(Color.WHITE);
        buttonGroup.setRadius((int)dpToPx(30));
        buttonGroup.setRipple(Color.BLACK);
        buttonGroup.setSelectedBackground(getResources().getColor(R.color.blue_500));
        buttonGroup.setSelectedBorder((int)dpToPx(2), Color.rgb(0x55, 0x55, 0x55), 0, 0);
        buttonGroup.setSelectedButtonRadius((int)dpToPx(30));
        buttonGroup.setSelectionAnimationDuration(1000);

        SegmentedButton yesButton = new SegmentedButton(this);
        yesButton.setText("Yes");
        yesButton.setPadding((int)dpToPx(8), (int)dpToPx(8), (int)dpToPx(8), (int)dpToPx(8));
        yesButton.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        yesButton.setTextColor(Color.BLACK);
        yesButton.setSelectedTextColor(Color.WHITE);
        yesButton.setTextSize(spToPx(24));
        buttonGroup.addView(yesButton);

        SegmentedButton maybeButton = new SegmentedButton(this);
        maybeButton.setText("Maybe");
        maybeButton.setPadding((int)dpToPx(8), (int)dpToPx(8), (int)dpToPx(8), (int)dpToPx(8));
        maybeButton.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        maybeButton.setTextColor(Color.BLACK);
        maybeButton.setSelectedTextColor(Color.WHITE);
        maybeButton.setTextSize(spToPx(24));
        buttonGroup.addView(maybeButton);

        SegmentedButton noButton = new SegmentedButton(this);
        noButton.setText("No");
        noButton.setPadding((int)dpToPx(8), (int)dpToPx(8), (int)dpToPx(8), (int)dpToPx(8));
        noButton.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        noButton.setTextColor(Color.BLACK);
        noButton.setSelectedTextColor(Color.WHITE);
        noButton.setTextSize(spToPx(24));
        buttonGroup.addView(noButton);

        buttonGroup.setPosition(1, false);
        binding.linearLayout.addView(buttonGroup);
        yesNoMaybePButtonGroup = buttonGroup;
    }

    private void setupStarWarsPButtonGroup()
    {
        SegmentedButtonGroup buttonGroup = new SegmentedButtonGroup(this);
        buttonGroup.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        ((LinearLayout.LayoutParams)buttonGroup.getLayoutParams()).setMargins((int)dpToPx(4), (int)dpToPx(4),
                                                                              (int)dpToPx(4), (int)dpToPx(4));
        ((LayoutParams)buttonGroup.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;
        buttonGroup.setElevation(dpToPx(2));
        buttonGroup.setBackground(getResources().getColor(R.color.red_400));
        buttonGroup.setRadius((int)dpToPx(2));
        buttonGroup.setRipple(getResources().getColor(R.color.red_200));
        buttonGroup.setSelectedBackground(getResources().getColor(R.color.green_200));
        buttonGroup.setDivider(Color.WHITE, (int)dpToPx(2), (int)dpToPx(10), (int)dpToPx(10));

        SegmentedButton button1 = new SegmentedButton(this);
        button1.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        button1.setPadding((int)dpToPx(20), (int)dpToPx(6), (int)dpToPx(20), (int)dpToPx(6));
        button1.setDrawable(getResources().getDrawable(R.drawable.ic_b8));
        buttonGroup.addView(button1);

        SegmentedButton button2 = new SegmentedButton(this);
        button2.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 2));
        button2.setPadding((int)dpToPx(45), (int)dpToPx(6), (int)dpToPx(45), (int)dpToPx(6));
        button2.setDrawable(getResources().getDrawable(R.drawable.ic_b9));
        buttonGroup.addView(button2);

        SegmentedButton button3 = new SegmentedButton(this);
        button3.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        button3.setPadding((int)dpToPx(20), (int)dpToPx(6), (int)dpToPx(20), (int)dpToPx(6));
        button3.setDrawable(getResources().getDrawable(R.drawable.ic_b11));
        buttonGroup.addView(button3);

        buttonGroup.setPosition(1, false);
        binding.linearLayout.addView(buttonGroup);
        starWarsPButtonGroup = buttonGroup;
    }

    private void setupCountPButtonGroup()
    {
        SegmentedButtonGroup buttonGroup = new SegmentedButtonGroup(this);
        buttonGroup.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        ((LinearLayout.LayoutParams)buttonGroup.getLayoutParams()).setMargins((int)dpToPx(32), (int)dpToPx(16),
                                                                              (int)dpToPx(32), (int)dpToPx(16));
        ((LayoutParams)buttonGroup.getLayoutParams()).gravity = Gravity.CENTER_HORIZONTAL;
        buttonGroup.setBorder(3, Color.LTGRAY, 0, 0);
        buttonGroup.setDivider(Color.DKGRAY, 5, 5, 5);
        buttonGroup.setOnPositionChangedListener(position -> {
            if (countPButtonGroup == null)
                return;

            countPButtonGroup.getButton(2).setText(countPButtonGroup.getButton(2).getText());
        });

        String[] names = {
                "Two",
                "One",
                "None"
        };

        for (String name : names)
        {
            SegmentedButton button = new SegmentedButton(this);
            button.setText(name);
            button.setTextSize(dpToPx(18));
            button.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            button.setPadding((int)dpToPx(20), (int)dpToPx(20), (int)dpToPx(20), (int)dpToPx(20));
            button.setTextColor(Color.BLACK);
            button.setSelectedTextColor(Color.WHITE);
            button.setSelectedBackgroundColor(getResources().getColor(R.color.colorPrimary));
            buttonGroup.addView(button);
        }

        binding.linearLayout.addView(buttonGroup);
        countPButtonGroup = buttonGroup;
    }
}
