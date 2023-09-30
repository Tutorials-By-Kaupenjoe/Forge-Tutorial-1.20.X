package net.kaupenjoe.tutorialmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.kaupenjoe.tutorialmod.entity.animations.ModAnimationDefinitions;
import net.kaupenjoe.tutorialmod.entity.custom.RhinoEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class RhinoModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart rhino;
	private final ModelPart head;

	public RhinoModel(ModelPart root) {
		this.rhino = root.getChild("rhino");
		this.head = rhino.getChild("body").getChild("torso").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition rhino = partdefinition.addOrReplaceChild("rhino", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 2.5F));

		PartDefinition body = rhino.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -38.0F, -26.0F, 20.0F, 24.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 41).addBox(-9.0F, -37.0F, -10.0F, 18.0F, 25.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(53, 67).addBox(-7.0F, -37.0F, 6.0F, 14.0F, 21.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -21.0F, -26.0F));

		PartDefinition skull = head.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(69, 29).addBox(-6.0F, -9.0F, -12.0F, 12.0F, 20.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(-1.0F, -1.0F, -15.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition horn = skull.addOrReplaceChild("horn", CubeListBuilder.create().texOffs(0, 102).addBox(-5.0F, -5.0F, -9.0F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 8.5F, -11.0F, -0.1745F, 0.0F, 0.0F));

		PartDefinition horn2 = horn.addOrReplaceChild("horn2", CubeListBuilder.create().texOffs(103, 15).addBox(-3.0F, -3.0F, -9.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, -9.0F, -0.4363F, 0.0F, 0.0F));

		PartDefinition left_ear = skull.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -5.0F, -0.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -8.0F, -9.5F));

		PartDefinition right_ear = skull.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-3.0F, -5.0F, -0.5F, 4.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.0F, -8.0F, -9.5F));

		PartDefinition left_eye = skull.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(55, 46).addBox(-0.45F, -0.4F, -0.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(57, 46).addBox(-0.55F, -1.6F, -1.1F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.8F, 5.1F, -9.4F, 0.2618F, 0.0F, 0.0F));

		PartDefinition left_eyelid = skull.addOrReplaceChild("left_eyelid", CubeListBuilder.create().texOffs(42, 85).addBox(-0.55F, -2.1F, -1.6F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.05F, 5.1F, -9.4F));

		PartDefinition right_eyelid = skull.addOrReplaceChild("right_eyelid", CubeListBuilder.create().texOffs(42, 85).mirror().addBox(-0.45F, -2.1F, -1.6F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-5.05F, 5.1F, -9.4F));

		PartDefinition right_eye = skull.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(55, 46).mirror().addBox(-0.55F, -0.4F, -0.9F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(57, 46).mirror().addBox(-0.45F, -1.6F, -1.1F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-5.8F, 5.1F, -9.4F, 0.2618F, 0.0F, 0.0F));

		PartDefinition tail = torso.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(16, 88).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 0.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(57, 0).addBox(-2.5F, 0.0F, 13.0F, 5.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -35.0F, 22.0F, -1.309F, 0.0F, 0.0F));

		PartDefinition left_back_leg = body.addOrReplaceChild("left_back_leg", CubeListBuilder.create().texOffs(73, 0).addBox(-4.5F, -4.5F, -5.0F, 9.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -28.5F, 15.5F));

		PartDefinition left_back_knee = left_back_leg.addOrReplaceChild("left_back_knee", CubeListBuilder.create().texOffs(98, 62).addBox(-3.5F, 0.0F, -1.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.5F, -3.0F));

		PartDefinition left_back_heel = left_back_knee.addOrReplaceChild("left_back_heel", CubeListBuilder.create().texOffs(54, 105).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, -0.5F));

		PartDefinition right_back_leg = body.addOrReplaceChild("right_back_leg", CubeListBuilder.create().texOffs(73, 0).mirror().addBox(-4.5F, -4.5F, -5.0F, 9.0F, 13.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, -28.5F, 15.5F));

		PartDefinition right_back_knee = right_back_leg.addOrReplaceChild("right_back_knee", CubeListBuilder.create().texOffs(98, 62).mirror().addBox(-3.5F, 0.0F, -1.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 8.5F, -3.0F));

		PartDefinition right_back_heel = right_back_knee.addOrReplaceChild("right_back_heel", CubeListBuilder.create().texOffs(54, 105).mirror().addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 10.0F, -0.5F));

		PartDefinition right_front_leg = body.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(100, 111).mirror().addBox(-3.5F, -3.0F, -3.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-7.0F, -17.0F, -20.5F));

		PartDefinition right_front_knee = right_front_leg.addOrReplaceChild("right_front_knee", CubeListBuilder.create().texOffs(54, 105).mirror().addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 7.0F, -2.5F));

		PartDefinition left_front_leg = body.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(100, 111).addBox(-3.5F, -3.0F, -3.0F, 7.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -17.0F, -20.5F));

		PartDefinition left_front_knee = left_front_leg.addOrReplaceChild("left_front_knee", CubeListBuilder.create().texOffs(54, 105).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 10.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -2.5F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.RHINO_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((RhinoEntity) entity).idleAnimationState, ModAnimationDefinitions.RHINO_IDLE, ageInTicks, 1f);
		this.animate(((RhinoEntity) entity).attackAnimationState, ModAnimationDefinitions.RHINO_ATTACK, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		rhino.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return rhino;
	}
}