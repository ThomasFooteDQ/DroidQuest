package com.droidquest.view.swing.material;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import com.droidquest.materials.*;

/**
 * Composite renderer that renders arbitrary Materials by selecting the correct
 * renderer from a map.
 */
public class MappedMaterialView implements MaterialView {
    private Map<Class<? extends Material>, MaterialView> renderers =
            new HashMap<Class<? extends Material>, MaterialView>();
    private MaterialView defaultRenderer;

    public static MaterialView create() {
        MappedMaterialView renderer = new MappedMaterialView();
        renderer.addRenderer(BatteryIn.class, new MaterialImageView(MaterialImages.createBatteryInImage()));
        renderer.addRenderer(BatteryOut.class, new BatteryOutView());
        renderer.addRenderer(BinaryLock.class, new BinaryLockView());
        renderer.addRenderer(BlueGridSwitch.class, new BlueGridSwitchView());
        renderer.addRenderer(CameraDisable.class, new MaterialImageView(MaterialImages.createCameraDisableImage()));
        renderer.addRenderer(CameraEnable.class, new MaterialImageView(MaterialImages.createCameraEnableImage()));
        renderer.addRenderer(CoinSlot.class, new MaterialImageView(MaterialImages.createCoinSlotImage()));
        renderer.addRenderer(CrystalRecharger.class, new MaterialImageView(MaterialImages.createCrystalRechargerImage()));
        renderer.addRenderer(DeactivatorSwitch.class, new DeactivatorSwitchView());
        renderer.addRenderer(ElevatorInPortal.class, new MaterialImageView(MaterialImages.createElevatorInPortalImage()));
        renderer.addRenderer(ElevatorLock.class, new MaterialImageView(MaterialImages.createElevatorLockImage()));
        renderer.addRenderer(ForceField.class, new ForceFieldView());
        renderer.addRenderer(HotWires.class, new HotWiresView());
        renderer.addRenderer(Lock.class, new LockView());
        renderer.addRenderer(MultiButton.class, new MultiButtonView());
        renderer.addRenderer(MultiSwitch.class, new MultiSwitchView());
        renderer.addRenderer(PanicButton.class, new PanicButtonView());
        renderer.addRenderer(PeriscopeDown.class, new MaterialImageView(MaterialImages.createPeriscopeDownImage()));
        renderer.addRenderer(PeriscopeUp.class, new MaterialImageView(MaterialImages.createPeriscopeUpImage()));
        renderer.addRenderer(PlayerBlocker.class, new PlayerBlockerView());
        renderer.addRenderer(Portal.class, new MaterialImageView(MaterialImages.createPortalImage()));
        renderer.addRenderer(Switch.class, new SwitchView());
        renderer.addRenderer(VendingSlot.class, new MaterialImageView(MaterialImages.createVendingSlotImage()));
        renderer.addRenderer(XitSlot.class, new MaterialImageView(MaterialImages.createXitSlotImage()));

        return renderer;
    }

    private MappedMaterialView() {
        defaultRenderer = new DefaultMaterialView();
    }

    public void addRenderer(Class<? extends Material> material, MaterialView renderer) {
        renderers.put(material, renderer);
    }

    @Override
    public void draw(Graphics g, Material material, int x, int y) {
        getRenderer(material).draw(g, material, x, y);
    }

    private MaterialView getRenderer(Material material) {
        MaterialView renderer = renderers.get(material.getClass());
        if (renderer != null) {
            return renderer;
        }

        return defaultRenderer;
    }
}
