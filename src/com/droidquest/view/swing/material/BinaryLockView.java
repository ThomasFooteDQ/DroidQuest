package com.droidquest.view.swing.material;

import java.awt.Image;

import com.droidquest.materials.BinaryLock;


public class BinaryLockView extends MaterialImageView<BinaryLock> {
    @Override
    protected Image getImage(BinaryLock material) {
        return MaterialImages.createBinaryLockImage(material.getColor());
    }
}
