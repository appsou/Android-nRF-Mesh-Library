/*
 * Copyright (c) 2018, Nordic Semiconductor
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package no.nordicsemi.android.nrfmeshprovisioner.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import no.nordicsemi.android.meshprovisioner.MeshManagerApi;
import no.nordicsemi.android.meshprovisioner.meshmessagestates.MeshModel;
import no.nordicsemi.android.meshprovisioner.meshmessagestates.ProvisionedMeshNode;
import no.nordicsemi.android.meshprovisioner.messages.ConfigAppKeyStatus;
import no.nordicsemi.android.meshprovisioner.messages.ConfigCompositionDataStatus;
import no.nordicsemi.android.meshprovisioner.utils.Element;
import no.nordicsemi.android.nrfmeshprovisioner.livedata.TransactionFailedLiveData;
import no.nordicsemi.android.nrfmeshprovisioner.repository.NodeConfigurationRepository;

public class NodeConfigurationViewModel extends ViewModel {

    private final NodeConfigurationRepository mNodeConfigurationRepository;
    private final NrfMeshRepository mNrfMeshRepository;

    @Inject
    NodeConfigurationViewModel(final NodeConfigurationRepository nodeConfigurationRepository, final NrfMeshRepository nrfMeshRepository) {
        this.mNodeConfigurationRepository = nodeConfigurationRepository;
        this.mNrfMeshRepository = nrfMeshRepository;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        mNodeConfigurationRepository.unbindService();
    }

    public ExtendedMeshNode getSelectedMeshNode(){
        return mNrfMeshRepository.getSelectedMeshNode();
    }

    /**
     * Set the element to be configured
     *
     * @param element {@link Element}
     */
    public void setSelectedElement(final Element element) {
        mNrfMeshRepository.setSelectedElement(element);
    }

    /**
     * Set the mesh model to be configured
     *
     * @param model {@link MeshModel}
     */
    public void setSelectedModel(final MeshModel model) {
        mNrfMeshRepository.setSelectedModel(model);
    }

    public LiveData<Boolean> isConnected() {
        return mNrfMeshRepository.isConnected();
    }

    public void setMeshNode(final ProvisionedMeshNode node) {
        mNrfMeshRepository.setSelectedMeshNode(node);
    }

    public MeshMessageLiveData getMeshMessageLiveData() {
        return mNrfMeshRepository.getMeshMessageLiveData();
    }

    public ProvisioningSettingsLiveData getProvisioningSettingLiveData() {
        return mNrfMeshRepository.getProvisioningSettingsLiveData();
    }

    public LiveData<TransactionFailedLiveData> getTransactionStatus() {
        return mNodeConfigurationRepository.getTransactionFailedLiveData();
    }

    public MeshManagerApi getMeshManagerApi() {
        return mNrfMeshRepository.getMeshManagerApi();
    }
}
