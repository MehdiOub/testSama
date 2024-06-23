/* eslint-disable @next/next/no-img-element */
"use client"
import { AppMenuItem } from '@/types';
import { MenuProvider } from './context/menucontext';
import AppMenuitem from './AppMenuitem';
import {AuthService} from '@/utils/zynerator/security/Auth.service';
import React, {useEffect, useState} from 'react';


const AppMenu = () => {

    const [model,setModel] = useState<AppMenuItem[]>([] as AppMenuItem[]);
    const authService = new AuthService();
        const modelAdmin: AppMenuItem[] = [
        {
            label: 'Home',
            items: [{label: 'Dashboard', icon: 'pi pi-fw pi-home', to: '/'}]
        },


        {
            label: 'Pages',
            icon: 'pi pi-fw pi-briefcase',
            to: '',
            items: [

                {
                    label: 'Auth',
                    icon: 'pi pi-fw pi-user',
                    items: [
                        {
                            label: 'Error',
                            icon: 'pi pi-fw pi-times-circle',
                            to: '/auth/error'
                        },
                        {
                            label: 'Access Denied',
                            icon: 'pi pi-fw pi-lock',
                            to: '/auth/access'
                        }
                    ]
                },
                {
                    label: 'Template Management',
                    icon: 'pi pi-fw pi-plus-circle',
                    items: [
                      {
                      label: 'Liste technology',
                     to: '/admin/view/template/technology'
                      },
                      {
                      label: 'Liste template',
                     to: '/admin/view/template/template'
                      },
                    ]
                    },
                {
                    label: 'Project Management',
                    icon: 'pi pi-fw pi-plus-circle',
                    items: [
                      {
                      label: 'Liste project',
                     to: '/admin/view/project/project'
                      },
                      {
                      label: 'Liste paiment collaborator state',
                     to: '/admin/view/collaborator/paiment-collaborator-state'
                      },
                      {
                      label: 'Liste project state',
                     to: '/admin/view/project/project-state'
                      },
                    ]
                    },
                {
                    label: 'Packaging Management',
                    icon: 'pi pi-fw pi-plus-circle',
                    items: [
                      {
                      label: 'Liste category packaging',
                     to: '/admin/view/packaging/category-packaging'
                      },
                      {
                      label: 'Liste packaging',
                     to: '/admin/view/packaging/packaging'
                      },
                    ]
                    },
                {
                    label: 'Collaborator Management',
                    icon: 'pi pi-fw pi-plus-circle',
                    items: [
                      {
                      label: 'Liste paiment collaborator',
                     to: '/admin/view/collaborator/paiment-collaborator'
                      },
                      {
                      label: 'Liste inscription collaborator state',
                     to: '/admin/view/collaborator/inscription-collaborator-state'
                      },
                      {
                      label: 'Liste collaborator',
                     to: '/admin/view/collaborator/collaborator'
                      },
                      {
                      label: 'Liste inscription collaborator',
                     to: '/admin/view/collaborator/inscription-collaborator'
                      },
                    ]
                    },
            ]
        },

		{
            label: 'Security Management',
            icon: 'pi pi-fw pi-plus-circle',
            items: [
                {
                    label: 'Liste role',
                    to: '/security/role'
                },
                //   {
                //   label: 'Liste model permission user',
                //  to: '/admin/view/security/model-permission-user'
                //   },
                {
                    label: 'Liste action permission',
                    to: '/security/action-permission'
                },
                {
                    label: 'Liste model permission',
                    to: '/security/model-permission'
                },
                {
                    label: 'Liste user',
                    to: '/security/user'
                },
            ]

        },

    ];
    const modelCollaborator: AppMenuItem[] = [
        {
            label: 'Home',
            items: [{label: 'Dashboard', icon: 'pi pi-fw pi-home', to: '/'}]
        },


        {
            label: 'Pages',
            icon: 'pi pi-fw pi-briefcase',
            to: '',
            items: [

                {
                    label: 'Auth',
                    icon: 'pi pi-fw pi-user',
                    items: [
                        {
                            label: 'Error',
                            icon: 'pi pi-fw pi-times-circle',
                            to: '/auth/error'
                        },
                        {
                            label: 'Access Denied',
                            icon: 'pi pi-fw pi-lock',
                            to: '/auth/access'
                        }
                    ]
                },
                {
                    label: 'Collaborator Management',
                    icon: 'pi pi-fw pi-plus-circle',
                    items: [
                      {
                      label: 'Liste paiment collaborator',
                     to: '/collaborator/view/collaborator/paiment-collaborator'
                      },
                      {
                      label: 'Liste collaborator',
                     to: '/collaborator/view/collaborator/collaborator'
                      },
                      {
                      label: 'Liste inscription collaborator',
                     to: '/collaborator/view/collaborator/inscription-collaborator'
                      },
                    ]
                    },
            ]
        },

		{
            label: 'Security Management',
            icon: 'pi pi-fw pi-plus-circle',
            items: [
                {
                    label: 'Liste role',
                    to: '/security/role'
                },
                //   {
                //   label: 'Liste model permission user',
                //  to: '/admin/view/security/model-permission-user'
                //   },
                {
                    label: 'Liste action permission',
                    to: '/security/action-permission'
                },
                {
                    label: 'Liste model permission',
                    to: '/security/model-permission'
                },
                {
                    label: 'Liste user',
                    to: '/security/user'
                },
            ]

        },

    ];

    useEffect(()=>{
        const roleConnectedUser = authService.getRoleConnectedUser();
        if(roleConnectedUser === 'ROLE_ADMIN'){
            setModel(modelAdmin)
        }
        if(roleConnectedUser === 'ROLE_COLLABORATOR'){
            setModel(modelCollaborator)
        }
    },[])

    return (
        <MenuProvider>
            <ul className="layout-menu">
                {model.map((item, i) => {
                    return !item?.seperator ? <AppMenuitem item={item} root={true} index={i} key={item.label}/> :
                        <li className="menu-separator"></li>;
                })}


            </ul>
        </MenuProvider>
    );
};


export default AppMenu;
