import { Af, System, TransactionPatternReference, BaseBusinessObjectNode, Access, LocalClientProxyFactory, IPropertyHandler } from "@sap/x4";

import BusinessObject = Af.Bof.BusinessObject;
import EsfTypes = Af.Core.Types;
import IChangeHandler = Af.Core.Provider.IChangeHandler;
import ILocalClientProxy = Af.Core.Lcp.ILocalClientProxy;
import IMessageHandler = Af.Core.Provider.IMessageHandler;
import IProviderContext = Af.Core.Provider.IProviderContext;
import MessageTypes = Af.Core.Message.Types;
import { BcAdministrationService } from "../../svc/admin/BcAdministrationService";
import { WorkspaceManagementService } from "../../svc/workspace/WorkspaceManagementService";
import { ModificationItem, EditMode } from "@sap/x4/sap/basis/esf/base/IAccess";
import { NodeId } from "@sap/x4/sap/basis/esf/base/IEsfTypes";

export class AccountsControllerServiceProvider extends BusinessObject {
    private lcp: Af.Core.Lcp.ILocalClientProxy;
    private lcpFacade: Af.Core.Lcp.ILocalClientProxyFacade;
    private isInitialized: boolean;
    private nodeIds: EsfTypes.NodeId[];
    private accountName: string;
    private workspaceLength: number = 0;


    public async modify(
        modifications: EsfTypes.ModificationItem[],
        changeHandler: IChangeHandler,
        messageHandler: IMessageHandler
    ): Promise<void> {
        this.getAccountName(modifications);
        return super.modify(modifications, changeHandler, messageHandler);
    }
    private getAccountName(modifications: EsfTypes.ModificationItem[]){
        modifications.forEach((mod) => {
            if(mod["boNodeName"] === "Root" && mod.data["Name"] ){
                this.accountName = mod["data"]["Name"];
            }
        });
    }
    public async retrieve(
        boNodeName: string,
        nodeIds: EsfTypes.NodeId[],
        editMode: EsfTypes.EditMode,
        requestedAttributes: string[],
        requestedImage?: EsfTypes.RequestedImage,
        messageHandler?: IMessageHandler,
        bufferSyncHandler?,
    ): Promise<Af.Core.Provider.RetrieveResponse> {
        let response: Af.Core.Provider.RetrieveResponse = {
            data: [],
            failedNodeIds: [],
        };
        this.nodeIds = nodeIds;
        response = await super.retrieve(
            boNodeName,
            nodeIds,
            editMode,
            requestedAttributes,
            requestedImage,
            messageHandler,
            bufferSyncHandler,
        );
        if (response.data[0]["Name"]) {
            //if here = edit 
            this.accountName = response.data[0]["Name"];
        }

        await this.initialize();
        let queryResult = await this.lcp.query(
            "AccountWorkspace",
            "QueryByParent",
            {
                pagingOptions: { pagingActive: false, startRow: 0 },
                sortOptions: []
            },
            [{
                attributeName: "ParentNodeID",
                option: EsfTypes.Options.Equal,
                sign: EsfTypes.Sign.Inclusive,
                low: nodeIds[0],
                high: undefined
            }],
            null,
            true
        );
        this.workspaceLength = queryResult.data.length;
        return response;
    }

    private async initialize(): Promise<void> {
        if (!this.isInitialized) {
            this.isInitialized = true;

            this.lcpFacade = Af.Core.Lcp.LocalClientProxyFactory.getLocalClientProxyFacade();
            this.lcp = await this.lcpFacade.getLocalClientProxy("AccountsController", "http://sap.com/x4/bc/admin");
        }
    }

    public async checkBeforeSave(rootNodeIds: EsfTypes.NodeId[], messageHandler?: IMessageHandler): Promise<boolean> {
        if (this.workspaceLength === 0) {
            this.accountName = this.accountName+" Workspace";
            await WorkspaceManagementService.Instance.createWorkspace(this.accountName, "S4PC");
        }

        return super.checkBeforeSave(rootNodeIds, messageHandler);
    }
}